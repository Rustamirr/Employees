package com.example.employees.view.list_employee

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.employees.App
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.network.NetworkInteractor
import com.example.employees.database.repository.RepositoryEmployee
import com.example.employees.database.repository.RepositorySpecialty
import com.example.employees.utils.EmployeeScreen
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EmployeeListFragmentPresenter: ViewModel(), EmployeeListFragmentContract.Presenter {
    @Inject
    lateinit var repositoryEmployee: RepositoryEmployee
    @Inject
    lateinit var repositorySpecialty: RepositorySpecialty
    @Inject
    lateinit var adapter: EmployeeAdapter
    @Inject
    lateinit var specialtyAdapter: ArrayAdapter<Specialty>
    @Inject
    lateinit var cicerone: Cicerone<Router>
    @Inject
    lateinit var networkInteractor: NetworkInteractor

    private var view: EmployeeListFragmentContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private var specialtySpinnerPosition: Int = 0

    init {
        App.instance.injector.getMainActivityComponent().inject(this)
        adapter.init(this)
        subscribeToUpdates()
    }

    override fun onViewCreated(view: EmployeeListFragmentContract.View) {
        this.view = view
        view.setAdapter(adapter)
        view.setSpecialtyAdapter(specialtyAdapter)
    }

    override fun onDestroyView() {
        view?.setAdapter(null)
        view?.setSpecialtyAdapter(null)
        view = null
    }

    override fun onCleared() {
        super.onCleared()
        unSubscribeToUpdates()
    }

    // Filter employees by specialty
    override fun specialtySpinnerItemSelected(position: Int) {
        if (specialtySpinnerPosition != position) {
            specialtySpinnerPosition = position
            updateAdapter(getEmployeeObservable(position))
        }
    }

    override fun onItemClick(employee: Employee) {
        cicerone.router.navigateTo(EmployeeScreen(employee.id))
    }

    private fun subscribeToUpdates() {
        loadFromNetwork()
    }

    private fun loadFromNetwork(){
        compositeDisposable.add(repositoryEmployee.getCount()
            .filter { it == 0L }
            .flatMap { networkInteractor.load() }
            .doOnError { Log.d("Employees", "Network error: $it") }
            .flatMapCompletable { repositoryEmployee.insertAll(it) }
            .doOnError { Log.d("Employees", "Database error: $it") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateAdapter(repositoryEmployee.getAll())
                updateSpecialtyAdapter()},
                {throwable -> view?.showToast("Error: $throwable")}
            )
        )
    }

    private fun unSubscribeToUpdates() { compositeDisposable.clear() }

    private fun updateAdapter(observable: Maybe<List<Employee>>) {
        compositeDisposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                adapter.setList(list)
                adapter.notifyDataSetChanged()
            })
    }

    private fun updateSpecialtyAdapter() {
        compositeDisposable.add(repositorySpecialty.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                specialtyAdapter.clear()
                specialtyAdapter.add(Specialty(0, "All")) // Add filter all
                specialtyAdapter.addAll(list)
                specialtyAdapter.notifyDataSetChanged()
            })
    }

    private fun getEmployeeObservable(specialtySpinnerPosition: Int): Maybe<List<Employee>> {
        val specialty = specialtyAdapter.getItem(specialtySpinnerPosition)!!
        return if (specialty.name == "All") repositoryEmployee.getAll() else repositoryEmployee.getBySpecialty(specialty)
    }
}
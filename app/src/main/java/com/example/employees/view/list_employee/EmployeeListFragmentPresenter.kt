package com.example.employees.view.list_employee

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.example.employees.App
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.repository.RepositoryEmployee
import com.example.employees.repository.RepositorySpecialty
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import com.example.employees.utils.EmployeeScreen

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
    //@Inject
    //lateinit var networkInteractor: NetworkInteractor

    private var view: EmployeeListFragmentContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private var specialtySpinnerPosition: Int = 0

    init {
        App.instance.injector.getMainActivityComponent().inject(this)
        adapter.onCreate(this)
        subscribeToUpdates()
    }

    override fun onViewCreated(view: EmployeeListFragmentContract.View) {
        this.view = view
        view.setAdapter(adapter)
        view.setSpecialtyAdapter(specialtyAdapter)
    }

    override fun onDestroyView() { view = null }

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

    private fun subscribeToUpdates() { updateSpecialtyAdapter() }

    private fun unSubscribeToUpdates() { compositeDisposable.clear() }

    private fun updateSpecialtyAdapter() {
        compositeDisposable.add(repositorySpecialty.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                specialtyAdapter.clear()
                specialtyAdapter.add(Specialty(0, "All")) // Add filter all
                specialtyAdapter.addAll(list)
                specialtyAdapter.notifyDataSetChanged()
                updateAdapter(getEmployeeObservable(0))
            })
    }

    private fun getEmployeeObservable(specialtySpinnerPosition: Int): Single<List<Employee>> {
        val specialty = specialtyAdapter.getItem(specialtySpinnerPosition)!!
        return if (specialty.name == "All") repositoryEmployee.getAll() else repositoryEmployee.getBySpecialty(specialty)
    }

    private fun updateAdapter(observable: Single<List<Employee>>) {
        compositeDisposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                adapter.setList(list)
                adapter.notifyDataSetChanged()
            })
    }

    override fun onItemClick(employee: Employee) {
        cicerone.router.navigateTo(EmployeeScreen(employee.id))
    }

    override fun download() {
        //compositeDisposable.add(repositoryEmployee.getCount()
         //   .filter { it == 0L }
          //  .flatMap { networkInteractor.loadEmployees() }
        /*compositeDisposable.add(networkInteractor.loadEmployees()
            .flatMapObservable { Observable.fromIterable(it.response) }
            .doOnNext {
                repositoryEmployee.insert(networkInteractor.networkEmployeeToEmployee(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view?.showToast("Completed successful") },
                { view?.showToast("Completed with errors: ${it.message}") })
        )*/
    }
}
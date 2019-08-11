package com.example.employees.view.employee

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employees.App
import com.example.employees.R
import com.example.employees.database.model.Employee
import com.example.employees.repository.RepositoryEmployee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class EmployeeFragmentPresenter(employeeId: Long?): ViewModel(), EmployeeFragmentContract.Presenter {
    @Inject
    lateinit var repositoryEmployee: RepositoryEmployee

    private var employee: Employee? = null
    private var view: EmployeeFragmentContract.View? = null
    private lateinit var disposable: Disposable

    private val dateTimeFormatter = DateTimeFormat.forPattern("dd/mm/yyyy")
    private val defaultBitmap = BitmapFactory.decodeResource(App.instance.resources, android.R.drawable.ic_menu_help)

    init {
        App.instance.injector.getMainActivityComponent().inject(this)
        if (employeeId != null) {
            subscribeToUpdate(employeeId)
        }
    }

    override fun onViewCreated(view: EmployeeFragmentContract.View) {
        this.view = view
        updateUI()
    }

    override fun onDestroyView() { view = null }

    override fun onCleared() {
        super.onCleared()
        unsubscribeToUpdate()
    }

    private fun subscribeToUpdate(employeeId: Long){
        disposable = repositoryEmployee.getById(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                employee = it
                updateUI()
            })
    }

    private fun unsubscribeToUpdate(){
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    private fun updateUI(){
        if (view != null && employee != null) {
            val view = view!!
            val employee = employee!!

            view.setAvatar(employee.avatar ?: defaultBitmap)
            view.setFirstName(employee.firstName)
            view.setLastName(employee.lastName)
            view.setBirthday(if (employee.birthday != null) dateTimeFormatter.print(employee.birthday) else "-")
            view.setAge(if (employee.age != null) employee.age.toString() + " years" else "-")
            view.setSpecialties(if (!employee.specialties.isEmpty()) employee.specialties.toString() else "-")
        }
    }

    class ModelFactory(private val employeeId: Long?): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EmployeeFragmentPresenter(employeeId) as T
        }
    }
}
package com.example.employees.di.component

import com.example.employees.di.ActivityScope
import com.example.employees.di.module.ActivityModule
import com.example.employees.view.list_employee.EmployeeListFragment
import com.example.employees.view.list_employee.EmployeeListFragmentPresenter
import com.example.employees.view.list_specialty.SpecialtyListFragmentPresenter
import dagger.Subcomponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainActivityComponent {
    fun getCicerone(): Cicerone<Router>
    fun inject(employeeListFragmentPresenter: EmployeeListFragmentPresenter)
    fun inject(specialtyListFragmentPresenter: SpecialtyListFragmentPresenter)
}
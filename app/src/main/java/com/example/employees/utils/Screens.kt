package com.example.employees.utils

import com.example.employees.view.employee.EmployeeFragment
import com.example.employees.view.list_employee.EmployeeListFragment
import com.example.employees.view.list_specialty.SpecialtyListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class EmployeeListScreen : SupportAppScreen() {
    override fun getFragment() = EmployeeListFragment.newInstance()
}

class EmployeeScreen(val id: Long) : SupportAppScreen() {
    override fun getFragment() = EmployeeFragment.newInstance(id)
}

class SpecialtyListScreen : SupportAppScreen() {
    override fun getFragment() = SpecialtyListFragment.newInstance()
}
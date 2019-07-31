package com.example.employees.utils

import androidx.fragment.app.Fragment
import com.example.employees.view.employee.EmployeeFragment
import com.example.employees.view.employee.EmployeeListFragment
import com.example.employees.view.specialty.SpecialtyListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class EmployeeListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = EmployeeListFragment.newInstance()
}

class EmployeeScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = EmployeeFragment.newInstance()
}

class SpecialtyListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = SpecialtyListFragment.newInstance()
}
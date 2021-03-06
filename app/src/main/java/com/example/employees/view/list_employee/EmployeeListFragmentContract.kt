package com.example.employees.view.list_employee

import android.widget.ArrayAdapter
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty

interface EmployeeListFragmentContract {
    interface View {
        fun setAdapter(adapter: EmployeeAdapter?)
        fun setSpecialtyAdapter(adapter: ArrayAdapter<Specialty>?)
        fun showToast(text: String)
        fun isPermissionsGranted(): Boolean
        fun requestPermissions()
    }
    interface Presenter{
        fun onCreate(view: View)
        fun onViewCreated(view: View)
        fun onDestroyView()
        fun specialtySpinnerItemSelected(position: Int)
        fun onItemClick(employee: Employee)
        fun permissionsGranted()
    }
}
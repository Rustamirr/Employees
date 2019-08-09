package com.example.employees.view.list_employee

import android.widget.ArrayAdapter
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty

interface EmployeeListFragmentContract {
    interface View {
        fun setAdapter(adapter: EmployeeAdapter)
        fun setSpecialtyAdapter(adapter: ArrayAdapter<Specialty>)
        fun showToast(text: String)
    }
    interface Presenter{
        fun onViewCreated(view: View)
        fun onDestroyView()
        fun specialtySpinnerItemSelected(position: Int)
        fun download()
        fun onItemClick(employee: Employee)
    }
}
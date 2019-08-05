package com.example.employees.view.list_employee

import android.widget.ArrayAdapter
import com.example.employees.database.model.Specialty

interface EmployeeListFragmentContract {
    interface View {
        fun setAdapter(adapter: EmployeeAdapter)
        fun setSpecialtyAdapter(adapter: ArrayAdapter<Specialty>)
    }
    interface Presenter{
        fun onViewCreated(view: View)
        fun onStart()
        fun onStop()
        fun onDestroyView()
        fun specialtySpinnerItemSelected(position: Int)
    }
}
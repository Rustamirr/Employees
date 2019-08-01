package com.example.employees.view.list_employee

interface EmployeeListFragmentContract {
    interface View {
        fun setAdapter(adapter: EmployeeAdapter)
    }
    interface Presenter{
        fun onViewCreated(view: View)
        fun onStart()
        fun onStop()
        fun onDestroyView()
    }
}
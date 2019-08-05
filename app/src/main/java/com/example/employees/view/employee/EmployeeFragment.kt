package com.example.employees.view.employee

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmployeeFragment: Fragment() {
    companion object {
        private const val ID = "EmployeeFragment_ID"

        fun newInstance(id: Long): EmployeeFragment {
            val arguments = Bundle()
            arguments.putLong(ID, id)
            val employeeFragment = EmployeeFragment()
            employeeFragment.arguments = arguments
            return employeeFragment
        }
    }

}
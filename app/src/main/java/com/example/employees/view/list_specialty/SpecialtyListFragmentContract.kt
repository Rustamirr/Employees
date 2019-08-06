package com.example.employees.view.list_specialty

import android.widget.ArrayAdapter
import com.example.employees.database.model.Specialty

interface SpecialtyListFragmentContract {
    interface View {
        fun setAdapter(adapter: ArrayAdapter<Specialty>)
    }
    interface Presenter {
        fun onViewCreated(view: View)
        fun onDestroyView()
    }
}
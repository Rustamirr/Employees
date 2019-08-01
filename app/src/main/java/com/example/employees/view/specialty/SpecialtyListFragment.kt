package com.example.employees.view.specialty

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment
import com.example.employees.App
import com.example.employees.database.model.Specialty

class SpecialtyListFragment: ListFragment() {
    //lateinit var presenter:
    companion object {
        fun newInstance() = SpecialtyListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val adapter = ArrayAdapter(App.instance, android.R.layout.simple_list_item_1, ))
    }
}
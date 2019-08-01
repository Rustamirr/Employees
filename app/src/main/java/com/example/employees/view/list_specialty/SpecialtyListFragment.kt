package com.example.employees.view.list_specialty

import android.os.Bundle
import androidx.fragment.app.ListFragment

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
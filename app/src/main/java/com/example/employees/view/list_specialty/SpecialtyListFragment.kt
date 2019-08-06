package com.example.employees.view.list_specialty

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProviders
import com.example.employees.database.model.Specialty

class SpecialtyListFragment: ListFragment(), SpecialtyListFragmentContract.View {
    companion object {
        fun newInstance() = SpecialtyListFragment()
    }
    private lateinit var presenter: SpecialtyListFragmentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ViewModelProviders.of(this).get(SpecialtyListFragmentPresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun setAdapter(adapter: ArrayAdapter<Specialty>) {
        listAdapter = adapter
    }
}
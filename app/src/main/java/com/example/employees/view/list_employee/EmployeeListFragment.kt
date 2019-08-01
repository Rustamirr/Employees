package com.example.employees.view.list_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.employees.R
import android.widget.Spinner

class EmployeeListFragment: Fragment(), EmployeeListFragmentContract.View {
    companion object {
        fun newInstance() = EmployeeListFragment()
    }
    @BindView(R.id.employee_list_fragment_recycler_view)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.employee_list_fragment_spinner_specialty)
    private lateinit var specialtySpinner: Spinner

    private lateinit var presenter: EmployeeListFragmentContract.Presenter
    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ViewModelProviders.of(this).get(EmployeeListFragmentPresenter::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.employee_list_fragment, container, false)
        unbinder = ButterKnife.bind(this, view)

        //specialtySpinner.setAdapter(spinnerAdapter)
        /*specialtySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                updateEmployeeList(id)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })*/
        specialtySpinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun setAdapter(adapter: EmployeeAdapter) {
        recyclerView.adapter = adapter
    }
}
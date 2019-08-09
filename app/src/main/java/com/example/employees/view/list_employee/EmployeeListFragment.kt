package com.example.employees.view.list_employee

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.employees.R
import android.widget.Spinner
import android.widget.Toast
import com.example.employees.database.model.Specialty

class EmployeeListFragment: Fragment(), EmployeeListFragmentContract.View {
    companion object {
        fun newInstance() = EmployeeListFragment()
    }
    @BindView(R.id.employee_list_fragment_recycler_view)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.employee_list_fragment_spinner_specialty)
    lateinit var specialtySpinner: Spinner

    private lateinit var presenter: EmployeeListFragmentContract.Presenter
    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = ViewModelProviders.of(this).get(EmployeeListFragmentPresenter::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.employee_list_fragment, container, false)
        unbinder = ButterKnife.bind(this, view)

        specialtySpinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                presenter.specialtySpinnerItemSelected(position)
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

    override fun onDestroyView() {
        presenter.onDestroyView()
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.employees_fragment, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
             R.id.menu_employees_fragment_download -> {
                presenter.download()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setAdapter(adapter: EmployeeAdapter) {
        recyclerView.adapter = adapter
    }

    override fun setSpecialtyAdapter(adapter: ArrayAdapter<Specialty>) {
        specialtySpinner.adapter = adapter
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
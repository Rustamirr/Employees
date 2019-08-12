package com.example.employees.view.list_employee

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.employees.R
import com.example.employees.database.model.Specialty

const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1

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
        presenter = ViewModelProviders.of(this).get(EmployeeListFragmentPresenter::class.java)
        presenter.onCreate(this)
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

    override fun setAdapter(adapter: EmployeeAdapter?) {
        recyclerView.adapter = adapter
    }

    override fun setSpecialtyAdapter(adapter: ArrayAdapter<Specialty>?) {
        specialtySpinner.adapter = adapter
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    // Permissions
    override fun isPermissionsGranted() = ContextCompat.checkSelfPermission(activity as Activity,
        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    override fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    presenter.permissionsGranted()
                }
            }
        }
    }
}
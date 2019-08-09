package com.example.employees.view.employee

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.employees.R
import org.joda.time.LocalDate

class EmployeeFragment: Fragment(), EmployeeFragmentContract.View {
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
    @BindView(R.id.employee_fragment_first_name)
    lateinit var tvFirstName: TextView

    @BindView(R.id.employee_fragment_last_name)
    lateinit var tvLastName: TextView

    @BindView(R.id.employee_fragment_birthday)
    lateinit var tvBirthday: TextView

    @BindView(R.id.employee_fragment_age)
    lateinit var tvAge: TextView

    @BindView(R.id.employee_fragment_avatar)
    lateinit var ivAvatar: ImageView

    @BindView(R.id.employee_fragment_specialties)
    lateinit var tvSpecialties: TextView

    lateinit var presenter: EmployeeFragmentContract.Presenter
    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ViewModelProviders.of(this).get(EmployeeFragmentPresenter::class.java)
        presenter.onCreate(arguments?.getLong(ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.employee_fragment, container, false)
        unbinder = ButterKnife.bind(this, view)
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

    override fun setAvatar(avatar: Bitmap) { ivAvatar.setImageBitmap(avatar) }

    override fun setFirstName(firstName: String) { tvFirstName.text = firstName }

    override fun setLastName(lastName: String) { tvLastName.text = lastName }

    override fun setBirthday(birthday: String) { tvBirthday.text = birthday }

    override fun setAge(age: String) { tvAge.text = age }

    override fun setSpecialties(specialties: String) { tvSpecialties.text = specialties }
}
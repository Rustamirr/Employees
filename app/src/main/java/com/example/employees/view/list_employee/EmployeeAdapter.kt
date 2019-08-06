package com.example.employees.view.list_employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.employees.R
import com.example.employees.database.model.Employee

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    private var list: List<Employee>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.employee_list_fragment_item_avatar)
        lateinit var ivAvatar: ImageView

        @BindView(R.id.employee_list_fragment_item_first_name)
        lateinit var tvFirstName: TextView

        @BindView(R.id.employee_list_fragment_item_last_name)
        lateinit var tvLastName: TextView

        @BindView(R.id.employee_list_fragment_item_age)
        lateinit var tvAge: TextView

        init {
            ButterKnife.bind(this, view)
        }

        fun bind(employee: Employee) {
            tvFirstName.text = employee.firstName
            tvLastName.text = employee.lastName
            tvAge.text = employee.age?.toString() ?: ""
        }
    }

    fun setList(list: List<Employee>){
        this.list = list
    }
}
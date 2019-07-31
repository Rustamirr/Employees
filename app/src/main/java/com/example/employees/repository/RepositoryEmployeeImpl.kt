package com.example.employees.repository

import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.model.Employee

class RepositoryEmployeeImpl(
    private val employeeDao: EmployeeDao): RepositoryEmployee {

    override fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }
}
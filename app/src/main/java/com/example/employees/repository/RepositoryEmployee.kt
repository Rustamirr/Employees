package com.example.employees.repository

import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.model.Employee

class RepositoryEmployee(
    private val employeeDao: EmployeeDao) {

    fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }
}
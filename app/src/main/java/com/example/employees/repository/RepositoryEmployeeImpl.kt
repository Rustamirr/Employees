package com.example.employees.repository

import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.model.Employee
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryEmployeeImpl(
    private val employeeDao: EmployeeDao): RepositoryEmployee {

    override fun insert(t: Employee) { employeeDao.insert(t) }
    override fun getAll(): Observable<List<Employee>> = employeeDao.getAll()
    override fun getById(id: Long): Single<Employee> = employeeDao.getById(id)
}
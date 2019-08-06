package com.example.employees.repository

import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryEmployeeImpl(
    private val employeeDao: EmployeeDao): RepositoryEmployee {

    override fun insert(t: Employee) { employeeDao.insert(t) }
    override fun getCount(): Maybe<Long> = employeeDao.getCount()
    override fun getAll(): Maybe<List<Employee>> = employeeDao.getAll()
    override fun getById(id: Long): Maybe<Employee> = employeeDao.getById(id)
    override fun getBySpecialty(specialty: Specialty): Maybe<List<Employee>> = employeeDao.getBySpecialty(specialty)
}
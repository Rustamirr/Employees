package com.example.employees.repository

import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Single

class RepositoryEmployeeImpl(
    private val employeeDao: EmployeeDao): RepositoryEmployee {

    override fun insert(t: Employee) { employeeDao.insert(t) }

    override fun getCount(): Single<Long> = employeeDao.getCount()

    override fun getAll(): Single<List<Employee>> = employeeDao.getAll()

    override fun getById(id: Long): Single<Employee> = employeeDao.getById(id)

    override fun getBySpecialty(specialty: Specialty): Single<List<Employee>> = employeeDao.getBySpecialty(specialty)
}
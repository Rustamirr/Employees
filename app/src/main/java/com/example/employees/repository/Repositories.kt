package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Observable
import io.reactivex.Single

interface BaseRepository<T> {
    fun insert(t: T)
    fun getAll(): Single<List<T>>
}

interface RepositoryEmployee: BaseRepository<Employee> {
    fun getById(id: Long): Single<Employee>
    fun getBySpecialty(specialty: Specialty): Single<List<Employee>>
}

interface RepositorySpecialty: BaseRepository<Specialty>

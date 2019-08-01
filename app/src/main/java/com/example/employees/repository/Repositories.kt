package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Observable
import io.reactivex.Single

interface BaseRepository<T> {
    fun insert(t: T)
    fun getAll(): Observable<List<T>>
}

interface RepositoryEmployee: BaseRepository<Employee> {
    fun getById(id: Long): Single<Employee>
}

interface RepositorySpecialty: BaseRepository<Specialty>

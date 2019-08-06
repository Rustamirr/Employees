package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Observable
import io.reactivex.Single

interface BaseRepository<T> {
    fun insert(t: T)
}

interface RepositoryEmployee: BaseRepository<Employee> {
    fun getAll(): Single<List<Employee>>
    fun getById(id: Long): Single<Employee>
    fun getBySpecialty(specialty: Specialty): Single<List<Employee>>
}

interface RepositorySpecialty: BaseRepository<Specialty> {
    fun getAll(): Observable<List<Specialty>>
}

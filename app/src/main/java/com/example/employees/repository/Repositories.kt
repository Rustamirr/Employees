package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface BaseRepository<T> {
    fun insert(t: T)
    fun getAll(): Single<List<T>>
}

interface RepositoryEmployee: BaseRepository<Employee> {
    fun insertAll(employees: List<Employee>): Completable
    fun getCount(): Maybe<Long>
    fun getById(id: Long): Single<Employee>
    fun getBySpecialty(specialty: Specialty): Single<List<Employee>>
}

interface RepositorySpecialty: BaseRepository<Specialty>

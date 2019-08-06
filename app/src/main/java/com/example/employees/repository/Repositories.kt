package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import io.reactivex.Maybe

interface BaseRepository<T> {
    fun insert(t: T)
    fun getAll(): Maybe<List<T>>
}

interface RepositoryEmployee: BaseRepository<Employee> {
    fun getCount(): Maybe<Long>
    fun getById(id: Long): Maybe<Employee>
    fun getBySpecialty(specialty: Specialty): Maybe<List<Employee>>
}

interface RepositorySpecialty: BaseRepository<Specialty>

package com.example.employees.repository

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty

interface RepositoryEmployee {
    fun insert(employee: Employee)
}

interface RepositorySpecialty {
    fun insert(specialty: Specialty)
}
package com.example.employees.repository

import com.example.employees.database.dao.SpecialtyDao
import com.example.employees.database.model.Specialty

class RepositorySpecialtyImpl(
    private val specialtyDao: SpecialtyDao): RepositorySpecialty {

    override fun insert(specialty: Specialty) {
        specialtyDao.insert(specialty)
    }


}
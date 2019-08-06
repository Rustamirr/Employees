package com.example.employees.repository

import com.example.employees.database.dao.SpecialtyDao
import com.example.employees.database.model.Specialty
import io.reactivex.Observable
import io.reactivex.Single

class RepositorySpecialtyImpl(
    private val specialtyDao: SpecialtyDao): RepositorySpecialty {

    override fun insert(t: Specialty) { specialtyDao.insert(t) }
    override fun getAll(): Observable<List<Specialty>> = specialtyDao.getAll()
}
package com.example.employees.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.employees.database.entity.EntityEmployeeSpecialty

@Dao
interface EmployeeSpecialtyDao {

    @Insert
    fun insert(entityEmployeeSpecialty: EntityEmployeeSpecialty)
}
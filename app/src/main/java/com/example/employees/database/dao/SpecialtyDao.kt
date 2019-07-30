package com.example.employees.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Specialty

@Dao
interface SpecialtyDao {

    fun insert(specialty: Specialty){
        insertEntitySpecialty(EntitySpecialty(specialty))
    }

    @Insert
    fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)
}
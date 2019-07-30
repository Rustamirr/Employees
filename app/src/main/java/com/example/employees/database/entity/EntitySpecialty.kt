package com.example.employees.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.employees.database.model.Specialty

@Entity(tableName = "Specialties")
data class EntitySpecialty(
    @PrimaryKey
    val id: Long,
    val name: String){

    @Ignore
    constructor(specialty: Specialty): this(specialty.id, specialty.name)
}

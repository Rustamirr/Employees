package com.example.employees.database.request

import androidx.room.Embedded
import androidx.room.Relation
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty

data class EmployeeSpecialty(
    @Embedded
    val entityEmployee: EntityEmployee,
    val specialtyId: Long?,
    val specialtyName: String?
)
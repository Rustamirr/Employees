package com.example.employees.database.request

import androidx.room.Embedded
import androidx.room.Relation
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty

class RequestEmployee(
    @Embedded
    val entityEmployee: EntityEmployee,

    @Relation(parentColumn = "id", entityColumn = "employeeId", entity = EntityEmployeeSpecialty::class)
    val entitySpecialties: List<EntitySpecialty>
)

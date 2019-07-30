package com.example.employees.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "EmployeesSpecialties",
    primaryKeys = ["employeeId", "specialtyId"],
    foreignKeys = [
        ForeignKey(
            entity = EntityEmployee::class,
            parentColumns = ["id"],
            childColumns = ["employeeId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = EntitySpecialty::class,
            parentColumns = ["id"],
            childColumns = ["specialtyId"],
            onDelete = CASCADE
        )
    ])
data class EntityEmployeeSpecialty(
    val employeeId: Long,
    val specialtyId: Long)
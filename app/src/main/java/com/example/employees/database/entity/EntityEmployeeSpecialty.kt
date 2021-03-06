package com.example.employees.database.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "EmployeesSpecialties",
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
    ],
    indices = [Index("employeeId"), Index("specialtyId")])

data class EntityEmployeeSpecialty(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val employeeId: Long,
    val specialtyId: Long)

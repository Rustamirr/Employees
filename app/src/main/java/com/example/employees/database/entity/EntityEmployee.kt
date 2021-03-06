package com.example.employees.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.employees.database.model.Employee

@Entity(tableName = "Employees")
data class EntityEmployee(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthday: Long?,
    val avatarPath: String?){

    @Ignore
    constructor(employee: Employee): this(employee.id, employee.firstName, employee.lastName,
        employee.birthday?.toDate()?.time, employee.avatarPath)
}
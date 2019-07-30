package com.example.employees.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.employees.database.model.Employee
import java.util.*

@Entity(tableName = "Employees")
data class EntityEmployee(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthday: Date,
    val avatarPath: String){

    @Ignore
    constructor(employee: Employee): this(employee.id, employee.firstName, employee.lastName, employee.birthday, employee.avatarPath)
}
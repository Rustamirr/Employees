package com.example.employees.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Employee

@Dao
abstract class EmployeeDao {

    @Transaction
    open fun insert(employee: Employee){
        val employeeId = insertEntityEmployee(EntityEmployee(employee))
        employee.specialties.forEach {
            insertEntityEmployeeSpecialty(EntityEmployeeSpecialty(employeeId, it.id))
        }
    }

    @Insert
    abstract fun insertEntityEmployee(entityEmployee: EntityEmployee): Long

    @Insert
    abstract fun insertEntityEmployeeSpecialty(entityEmployeeSpecialty: EntityEmployeeSpecialty)
}
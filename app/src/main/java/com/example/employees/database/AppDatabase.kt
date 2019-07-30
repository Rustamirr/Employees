package com.example.employees.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.dao.SpecialtyDao
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty

@Database(entities = [EntityEmployee::class, EntitySpecialty::class, EntityEmployeeSpecialty::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
    abstract fun getSpecialtyDao(): SpecialtyDao
}
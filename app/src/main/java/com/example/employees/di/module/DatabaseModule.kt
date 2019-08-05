package com.example.employees.di.module

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.employees.database.AppDatabase
import com.example.employees.database.dao.EmployeeDao
import com.example.employees.database.dao.SpecialtyDao
import com.example.employees.repository.RepositoryEmployee
import com.example.employees.repository.RepositoryEmployeeImpl
import com.example.employees.repository.RepositorySpecialty
import com.example.employees.repository.RepositorySpecialtyImpl
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class DatabaseModule(private val databaseName: String) {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase){
                    super.onCreate(db)
                    initDB(db)
                    createTestData(db)
                }
            })
            .build()

    private fun initDB(db: SupportSQLiteDatabase){
        // Filter item
        val cv = ContentValues()
        cv.put("id", "1")
        cv.put("name", "All")
        db.insert("Specialties", OnConflictStrategy.ABORT, cv)
    }

    private fun createTestData(db: SupportSQLiteDatabase){
        // Employees
        val cv = ContentValues()
        cv.put("firstName", "Ivan")
        cv.put("lastName", "Ivanov")
        val employeeId1 = db.insert("Employees", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("firstName", "Alex")
        cv.put("lastName", "Alexandrov")
        val employeeId2 = db.insert("Employees", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("firstName", "Sara")
        cv.put("lastName", "Connar")
        db.insert("Employees", OnConflictStrategy.ABORT, cv)

        // Specialties
        cv.clear()
        cv.put("name", "Specialty 1")
        val specialtyId1 = db.insert("Specialties", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("name", "Specialty 2")
        val specialtyId2 = db.insert("Specialties", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("name", "Specialty 3")
        db.insert("Specialties", OnConflictStrategy.ABORT, cv)

        // Relations
        cv.clear()
        cv.put("employeeId", employeeId1)
        cv.put("specialtyId", specialtyId1)
        db.insert("EmployeesSpecialties", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("employeeId", employeeId1)
        cv.put("specialtyId", specialtyId2)
        db.insert("EmployeesSpecialties", OnConflictStrategy.ABORT, cv)

        cv.clear()
        cv.put("employeeId", employeeId2)
        cv.put("specialtyId", specialtyId1)
        db.insert("EmployeesSpecialties", OnConflictStrategy.ABORT, cv)
    }

    @Provides
    fun provideEmployeeDao(appDatabase: AppDatabase): EmployeeDao = appDatabase.getEmployeeDao()

    @Provides
    fun provideSpecialtyDao(appDatabase: AppDatabase): SpecialtyDao = appDatabase.getSpecialtyDao()

    @Singleton
    @Provides
    fun provideRepositoryEmployee(employeeDao: EmployeeDao): RepositoryEmployee = RepositoryEmployeeImpl(employeeDao)

    @Singleton
    @Provides
    fun provideRepositorySpecialty(specialtyDao: SpecialtyDao): RepositorySpecialty = RepositorySpecialtyImpl(specialtyDao)
}
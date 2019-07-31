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
                    // Инициализацию базы необходимо выполнять в потоке UI, чтобы база заполнилась
                    // еще до выполнения запросов на выборку
                    val cv = ContentValues()
                    cv.put("name", "Specialty1")
                    val specialtyId = db.insert("Specialties", OnConflictStrategy.ABORT, cv)

                    cv.clear()
                    cv.put("firstName", "Иванов")
                    cv.put("lastName", "Иван")
                    cv.put("birthday", Date().time)
                    cv.put("avatarPath", "")
                    val employeeId = db.insert("Employees", OnConflictStrategy.ABORT, cv)

                    cv.clear()
                    cv.put("employeeId", employeeId)
                    cv.put("specialtyId", specialtyId)
                    db.insert("EmployeesSpecialties", OnConflictStrategy.ABORT, cv)
                }
            })
            .build()

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
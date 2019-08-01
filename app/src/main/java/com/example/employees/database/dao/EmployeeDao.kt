package com.example.employees.database.dao

import androidx.room.*
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.database.request.EmployeeSpecialty
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

@Dao
abstract class EmployeeDao {
    @Transaction
    open fun insert(employee: Employee){
        val employeeId = insertEntityEmployee(EntityEmployee(employee))
        employee.specialties.forEach {
            insertEntitySpecialty(EntitySpecialty(it))
            insertEntityEmployeeSpecialty(EntityEmployeeSpecialty(employeeId = employeeId, specialtyId = it.id))
        }
    }

    fun getAll(): Observable<List<Employee>> =
        requestAllEmployeeSpecialty().map { list: List<EmployeeSpecialty> ->
            val result = ArrayList<Employee>()
            val specialties = ArrayList<Specialty>()

            for (i in 0 until list.size){
                val employeeSpecialty = list[i]
                val entityEmployee = employeeSpecialty.entityEmployee

                if (employeeSpecialty.specialtyId != null && employeeSpecialty.specialtyName != null) {
                    specialties.add(Specialty(employeeSpecialty.specialtyId, employeeSpecialty.specialtyName))
                }

                if (i == list.size - 1 || entityEmployee.id != list[i + 1].entityEmployee.id){
                    result.add(Employee(entityEmployee.id, entityEmployee.firstName, entityEmployee.lastName,
                        if (entityEmployee.birthday != null) Date(entityEmployee.birthday) else null, entityEmployee.avatarPath, specialties))
                    specialties.clear()
                }
            }
            result
        }

    fun getById(id: Long): Single<Employee> =
        requestEmployeeSpecialtyById(id).map { list: List<EmployeeSpecialty> ->
            if (list.isEmpty()) {
                throw Exception("Not found employee by id: $id")
            }
            val entityEmployee = list[0].entityEmployee
            val specialties = ArrayList<Specialty>()
            list.forEach{
                if (it.specialtyId != null && it.specialtyName != null) {
                    specialties.add(Specialty(it.specialtyId, it.specialtyName))
                }
            }
            Employee(entityEmployee.id, entityEmployee.firstName, entityEmployee.lastName,
                if (entityEmployee.birthday != null) Date(entityEmployee.birthday) else null, entityEmployee.avatarPath, specialties)
        }

    @Insert
    abstract fun insertEntityEmployee(entityEmployee: EntityEmployee): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)

    @Insert
    abstract fun insertEntityEmployeeSpecialty(entityEmployeeSpecialty: EntityEmployeeSpecialty)

    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Order by Employees.id""")
    abstract fun requestAllEmployeeSpecialty(): Observable<List<EmployeeSpecialty>>

    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Where Employees.id = :id""")
    abstract fun requestEmployeeSpecialtyById(id: Long): Single<List<EmployeeSpecialty>>
}
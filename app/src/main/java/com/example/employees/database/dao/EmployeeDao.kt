package com.example.employees.database.dao

import androidx.room.*
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.database.request.EmployeeSpecialty
import io.reactivex.Observable
import java.util.*

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

    /*fun getAll(): Observable<List<Employee>> =
        getAllRequestEmployee().map {list: List<EmployeeSpecialty> ->
            /*list.map {requestEmployee ->
                val entityEmployee = requestEmployee.entityEmployee
                val entitySpecialties = requestEmployee.entitySpecialties

                val specialties = entitySpecialties.map { Specialty(it.id, it.name) }

                Employee(entityEmployee.id, entityEmployee.firstName, entityEmployee.lastName, Date(entityEmployee.birthday),
                    entityEmployee.avatarPath, specialties)
            }*/
        }*/

    @Insert
    abstract fun insertEntityEmployee(entityEmployee: EntityEmployee): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)

    @Insert
    abstract fun insertEntityEmployeeSpecialty(entityEmployeeSpecialty: EntityEmployeeSpecialty)

    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId""")
    abstract fun getAllRequestEmployee(): Observable<List<EmployeeSpecialty>>
}
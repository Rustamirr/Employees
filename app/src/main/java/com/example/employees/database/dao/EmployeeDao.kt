package com.example.employees.database.dao

import androidx.room.*
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.database.request.EmployeeSpecialty
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.LocalDate
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

    fun getAll(): Maybe<List<Employee>> = requestAllEmployeeSpecialty().map { employeeSpecialtyToEmployee(it) }

    fun getBySpecialty(specialty: Specialty): Maybe<List<Employee>> =
        requestEmployeeSpecialtyBySpecialty(specialty.id).map { employeeSpecialtyToEmployee(it) }

    fun getById(id: Long): Maybe<Employee> = requestEmployeeSpecialtyById(id).map { employeeSpecialtyToEmployee(it).first() }


    private fun employeeSpecialtyToEmployee(list: List<EmployeeSpecialty>): List<Employee> {
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
                    if (entityEmployee.birthday != null) LocalDate(entityEmployee.birthday) else null,
                    entityEmployee.avatarPath, null, specialties))
                if (i < list.size - 1) {
                    specialties.clear()
                }
            }
        }
        return result
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntityEmployee(entityEmployee: EntityEmployee): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntityEmployeeSpecialty(entityEmployeeSpecialty: EntityEmployeeSpecialty)

    // Сортировка по id упрощает обход выборки (после левых соединений выборка будет содержать дублирующиеся данные по employee)
    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Order by Employees.id""")
    abstract fun requestAllEmployeeSpecialty(): Maybe<List<EmployeeSpecialty>>

    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Where Employees.id = :employeeId""")
    abstract fun requestEmployeeSpecialtyById(employeeId: Long): Maybe<List<EmployeeSpecialty>>

    // Сортировка по id упрощает обход выборки (после левых соединений выборка будет содержать дублирующиеся данные по employee)
    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Where Specialties.id = :specialtyId
        Order by Employees.id""")
    abstract fun requestEmployeeSpecialtyBySpecialty(specialtyId: Long): Maybe<List<EmployeeSpecialty>>

    @Query("Select Count(*)")
    abstract fun getCount(): Maybe<Long>
}
package com.example.employees.database.dao

import androidx.room.*
import com.example.employees.database.entity.EntityEmployee
import com.example.employees.database.entity.EntityEmployeeSpecialty
import com.example.employees.database.entity.EntitySpecialty
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.database.request.EmployeeSpecialty
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.functions.Action
import org.joda.time.LocalDate

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

    fun insertAll(employees: List<Employee>): Completable = Completable.fromAction { employees.forEach { insert(it) } }

    fun getAll(): Single<List<Employee>> = requestAllEmployeeSpecialty().map { employeeSpecialtyToEmployee(it) }

    fun getBySpecialty(specialty: Specialty): Single<List<Employee>> =
        requestEmployeeSpecialtyBySpecialty(specialty.id).map { employeeSpecialtyToEmployee(it) }

    fun getById(id: Long): Single<Employee> = requestEmployeeSpecialtyById(id).map { employeeSpecialtyToEmployee(it).first() }

    @Insert
    abstract fun insertEntityEmployee(entityEmployee: EntityEmployee): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertEntitySpecialty(entitySpecialty: EntitySpecialty)

    @Insert
    abstract fun insertEntityEmployeeSpecialty(entityEmployeeSpecialty: EntityEmployeeSpecialty)

    private fun employeeSpecialtyToEmployee(list: List<EmployeeSpecialty>): List<Employee> {
        val result = ArrayList<Employee>()
        val specialties = HashSet<Specialty>()

        for (i in 0 until list.size){
            val employeeSpecialty= list[i]
            val entityEmployee= employeeSpecialty.entityEmployee

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

    // Сортировка по id упрощает обход выборки (после левых соединений выборка будет содержать дублирующиеся данные по employee)
    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Order by Employees.id""")
    abstract fun requestAllEmployeeSpecialty(): Single<List<EmployeeSpecialty>>

    // Сортировка по id упрощает обход выборки (после левых соединений выборка будет содержать дублирующиеся данные по employee)
    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Where Specialties.id = :specialtyId
        Order by Employees.id""")
    abstract fun requestEmployeeSpecialtyBySpecialty(specialtyId: Long): Single<List<EmployeeSpecialty>>

    @Query("""Select Employees.*, Specialties.id as specialtyId, Specialties.name as specialtyName From Employees
        left join EmployeesSpecialties on Employees.id = employeeId 
        left join Specialties on Specialties.id = specialtyId
        Where Employees.id = :employeeId""")
    abstract fun requestEmployeeSpecialtyById(employeeId: Long): Single<List<EmployeeSpecialty>>

    @Query("Select Count(*) From Employees")
    abstract fun getCount(): Maybe<Long>
}
/*package com.example.employees.interactor

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.network.NetworkApi
import com.example.employees.network.NetworkEmployee
import com.example.employees.network.NetworkSpecialty
import org.joda.time.LocalDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NetworkInteractor(
    private val networkApi: NetworkApi) {

    fun loadEmployees() = networkApi.getEmployees()

    fun networkEmployeeToEmployee(networkEmployee: NetworkEmployee) = Employee(
        firstName = getImprovedEmployeeName(networkEmployee.firstName),
        lastName = getImprovedEmployeeName(networkEmployee.lastName),
        birthday = stringToDate(networkEmployee.birthday),
        avatarPath = null,
        avatar = null,
        specialties = networkSpecialtyToSpecialty(networkEmployee.specialties))

    private fun getImprovedEmployeeName(name: String): String {
        var result = ""
        if (name != "") {
            result = name.toLowerCase()
            result = result.substring(0, 1).toUpperCase() + result.substring(1)
        }
        return result
    }

    private fun stringToDate(dateStr: String): LocalDate? {
        // Определим формат даты по расположению разделителя
        val format = when (dateStr.trim().indexOf("-")){
            4 -> SimpleDateFormat("yyyy-MM-dd", Locale.US)
            2 -> SimpleDateFormat("dd-MM-yyyy", Locale.US)
            else -> throw Exception("Unknown birthday date format")
        }
        return LocalDate(format.parse(dateStr))
    }

    private fun networkSpecialtyToSpecialty(list: List<NetworkSpecialty>): Set<Specialty> = list.map { Specialty(it.id, it.name) }
}*/
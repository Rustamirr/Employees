package com.example.employees.interactor

import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.network.NetworkApi
import io.reactivex.Single
import org.joda.time.LocalDate
import java.text.SimpleDateFormat
import java.util.*

class NetworkInteractor(
    private val networkApi: NetworkApi) {

    fun load(): Single<List<Employee>> = networkApi.getEmployees()
        .map {networkResponse ->
            networkResponse.response.map {networkEmployee ->
                Employee(improveName(networkEmployee.firstName),
                    improveName(networkEmployee.lastName),
                    stringToDate(networkEmployee.birthday),
                    null,
                    null,
                    networkEmployee.specialties.map { Specialty(it.id, it.name) }.toHashSet())
            }
        }

    private fun improveName(name: String): String {
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
}
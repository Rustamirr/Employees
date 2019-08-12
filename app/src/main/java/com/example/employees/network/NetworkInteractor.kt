package com.example.employees.network

import android.graphics.BitmapFactory
import com.example.employees.database.model.Employee
import com.example.employees.database.model.Specialty
import com.example.employees.utils.saveToFile
import io.reactivex.Maybe
import org.joda.time.LocalDate
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class NetworkInteractor(
    private val networkApi: NetworkApi) {

    fun load(): Maybe<List<Employee>> = networkApi.getEmployees()
        .map {networkResponse ->
            var counter = 0
            networkResponse.response.map {networkEmployee ->
                counter++
                val bitmap = try {
                    BitmapFactory.decodeStream(URL(networkEmployee.avatarUrl).openConnection().getInputStream())
                } catch (exception: Exception) {
                    null
                }
                Employee(improveName(networkEmployee.firstName),
                    improveName(networkEmployee.lastName),
                    stringToDate(networkEmployee.birthday),
                    if (bitmap != null) saveToFile(bitmap, counter.toString()) else null,
                    bitmap,
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

    private fun stringToDate(dateStr: String?): LocalDate? {
        return if (dateStr == null || dateStr.isEmpty()) null
        else {
            // Определим формат даты по расположению разделителя
            val format = when (dateStr.trim().indexOf("-")) {
                4 -> SimpleDateFormat("yyyy-MM-dd", Locale.US)
                2 -> SimpleDateFormat("dd-MM-yyyy", Locale.US)
                else -> return null
            }
            LocalDate(format.parse(dateStr))
        }
    }
}
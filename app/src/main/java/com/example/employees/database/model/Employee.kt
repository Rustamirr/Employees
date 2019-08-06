package com.example.employees.database.model

import android.graphics.Bitmap
import org.joda.time.LocalDate
import org.joda.time.Years

data class Employee(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate?,
    val avatarPath: String?,
    var avatar: Bitmap?,
    val specialties: List<Specialty>) {

    val age: Int?
        get() = if (birthday != null) Years.yearsBetween(birthday, LocalDate.now()).years else null
}

package com.example.employees.database.model

import android.graphics.Bitmap
import org.joda.time.Instant
import org.joda.time.LocalDate
import org.joda.time.Period

data class Employee(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate?,
    val avatarPath: String?,
    var avatar: Bitmap?,
    val specialties: List<Specialty>) {

    val age: Int?
        get() {
            Instant.now().to
        }
}

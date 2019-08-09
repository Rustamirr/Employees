package com.example.employees.database.model

import android.graphics.Bitmap
import org.joda.time.LocalDate
import org.joda.time.Years

data class Employee(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate?,
    val avatarPath: String?,
    var avatar: Bitmap?,
    val specialties: Set<Specialty>) {

    val age: Int?
        get() = if (birthday != null) Years.yearsBetween(birthday, LocalDate.now()).years else null

    constructor(firstName: String, lastName: String, birthday: LocalDate?, avatarPath: String?, avatar: Bitmap?,
                specialties: Set<Specialty>): this(0, firstName, lastName, birthday, avatarPath, avatar, specialties)
}

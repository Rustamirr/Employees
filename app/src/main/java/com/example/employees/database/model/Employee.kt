package com.example.employees.database.model

import java.util.*

data class Employee(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val birthday: Date?,
    val avatarPath: String?,
    val specialties: List<Specialty>)
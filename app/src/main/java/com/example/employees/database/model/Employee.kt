package com.example.employees.database.model

import java.util.*

data class Employee(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthday: Date,
    val avatarPath: String,
    val specialties: Set<Specialty>) {

    constructor(firstName: String, lastName: String, birthday: Date, avatarPath: String, specialties: Set<Specialty>): this (
        0, firstName, lastName, birthday, avatarPath, specialties)
}
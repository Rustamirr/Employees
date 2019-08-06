package com.example.employees.database.model

data class Specialty(
    val id: Long,
    val name: String) {

    override fun toString() = name
}


package com.example.employees.database.model

data class Specialty(
    val id: Long,
    val name: String) {
    val servicePropertyAll: Boolean = (id == 1L)

    override fun toString() = name
}


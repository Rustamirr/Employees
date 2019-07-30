package com.example.employees.network

import com.google.gson.annotations.SerializedName

data class NetworkSpecialty(
    @SerializedName("specialty_id")
    val id: Long,
    val name: String
)
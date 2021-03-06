package com.example.employees.network

import com.google.gson.annotations.SerializedName

data class NetworkEmployee(
    @SerializedName("f_name")
    val firstName: String,

    @SerializedName("l_name")
    val lastName: String,

    val birthday: String,

    @SerializedName("avatr_url")
    val avatarUrl: String,

    @SerializedName("specialty")
    val specialties: Set<NetworkSpecialty>
)
package com.example.employees.network

import io.reactivex.Maybe
import retrofit2.http.GET

interface NetworkApi {
    @GET("testTask.json")
    fun getEmployees(): Maybe<NetworkResponse>
}
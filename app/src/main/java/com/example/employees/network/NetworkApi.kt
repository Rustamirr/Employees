package com.example.employees.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("testTask.json")
    fun getEmployees(): Single<NetworkResponse>
}
package com.example.employees.interactor

import com.example.employees.network.NetworkApi
import com.example.employees.network.NetworkResponse
import io.reactivex.Maybe

class NetworkInteractor(
    private val networkApi: NetworkApi) {

    fun makeRequest(): Maybe<NetworkResponse> = networkApi.getEmployees()
}
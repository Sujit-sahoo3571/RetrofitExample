package com.futurebrains.retrofitexample.data.repository

import com.futurebrains.retrofitexample.data.api.ApiService

class MainRepostiory(private val apiService: ApiService) {
    suspend fun getUsers () = apiService.getUsers()
}
package com.futurebrains.retrofitexample.data.api

import com.futurebrains.retrofitexample.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>
}

// retrofit Network call interface instance
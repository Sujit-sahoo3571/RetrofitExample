package com.futurebrains.retrofitexample.data.model

import com.google.gson.annotations.SerializedName

//data class User(val avatar: String, val email: String, val id: String, val name: String)

data class User(
    @SerializedName("avatar")
    val image : String,
    @SerializedName("email")
    val userEmail : String,
    @SerializedName("id")
    val userId : String,
    @SerializedName("name")
    val userName: String


)
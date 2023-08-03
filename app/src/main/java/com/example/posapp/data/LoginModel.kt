package com.example.posapp.data

data class LoginModel(
    val userId: String,
    val username: String,
    val email: String,
    val profilePicture: String? = ""
)

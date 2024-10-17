package com.example.Project

data class RegisterRequest(
    val name: String,
    val studentNumber: String,
    val email: String,
    val password: String
)

data class Description(
    val fa: String,
    val en: String
)

data class ApiResponse(
    val status: Int,
    val description: Description,
    val data: Any?
)

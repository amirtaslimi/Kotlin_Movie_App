package com.example.Project.shared_component.APIServices
import com.example.Project.ApiResponse
import com.example.Project.GenreResponse
import com.example.Project.MoviesResponse
import com.example.Project.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIServiceUser {
    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): ApiResponse
}
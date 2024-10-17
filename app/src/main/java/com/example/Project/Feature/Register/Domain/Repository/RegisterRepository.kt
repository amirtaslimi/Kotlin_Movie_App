package com.example.Project.Feature.Register.Domain.Repository

import com.example.Project.ApiResponse
import com.example.Project.RegisterRequest
import com.example.Project.shared_component.APIServices.APIServiceUser
import javax.inject.Inject


class RegisterRepository @Inject constructor(private val api: APIServiceUser) {
    suspend fun registerUser(request: RegisterRequest): Result<ApiResponse> {
        return try {
            val response = api.registerUser(request)
            if (response.status == 200) {
                Result.success(response)
            } else {
                Result.failure(Throwable(response.description.en))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

package com.example.Project.shared_component.Repository

import com.example.Project.GenreResponse
import com.example.Project.MovieResponse
import com.example.Project.MoviesResponse
import com.example.Project.shared_component.APIServices.APIServiceMovie
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: APIServiceMovie) {
    suspend fun getAllMovies(genreId:Int): Result<MoviesResponse> {
        val response = api.getAllMovies(genreId)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }

    suspend fun getMovieById(movieId: Int): Result<MovieResponse> {
        val response = api.getMovieById(movieId)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }

    suspend fun getAllGenres(): Result<GenreResponse>? {
        val response = api.getAllGenres()
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }


}
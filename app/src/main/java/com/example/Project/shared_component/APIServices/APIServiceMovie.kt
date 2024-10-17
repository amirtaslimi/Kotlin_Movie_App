package com.example.Project.shared_component.APIServices
import com.example.Project.GenreResponse
import com.example.Project.MovieResponse
import com.example.Project.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface APIServiceMovie {

    @GET("genres/{id}/movies")
    suspend fun getAllMovies(@Path("id") id: Int): Response<MoviesResponse>

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<MovieResponse>

    @GET("genres")
    suspend fun getAllGenres(): Response<GenreResponse>
}
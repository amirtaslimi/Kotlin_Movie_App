package com.example.Project


import com.google.gson.annotations.SerializedName


data class GenreResponse(
    val status: Int,
    val data: List<GenreItem>
)



data class GenreItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

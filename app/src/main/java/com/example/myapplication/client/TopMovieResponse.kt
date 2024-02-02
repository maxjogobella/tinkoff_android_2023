package com.example.myapplication.client

import com.google.gson.annotations.SerializedName

data class TopMovieResponse(
    @SerializedName("items")
    val listOfTopMovies : List<Movie>? = null
)
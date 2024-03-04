package com.example.myapplication.data.retrofit.model

import com.google.gson.annotations.SerializedName

data class TopMovieResponse(
    @SerializedName("items")
    val listOfTopMovies : List<MovieTDO>? = null
)
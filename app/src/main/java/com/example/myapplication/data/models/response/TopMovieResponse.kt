package com.example.myapplication.data.models.response

import com.example.myapplication.data.models.movie.MovieTDO
import com.google.gson.annotations.SerializedName

data class TopMovieResponse(
    @SerializedName("items")
    val listOfTopMovies : List<MovieTDO>? = null
)
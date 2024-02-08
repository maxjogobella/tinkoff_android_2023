package com.example.myapplication.domain.models

import com.example.myapplication.domain.repository.MovieRepository

data class Movie(
    val id : Int? = null,
    val name : String? = null,
    val url : String? = null,
    val year : Int? = null,
    val listOfGenre : List<Genres>? = null
)

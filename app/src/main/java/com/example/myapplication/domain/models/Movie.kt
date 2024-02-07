package com.example.myapplication.domain.models

data class Movie(
    val id : Int? = null,
    val name : String? = null,
    val url : String? = null,
    val year : Int? = null,
    val listOfGenre : List<Genres>? = null
)

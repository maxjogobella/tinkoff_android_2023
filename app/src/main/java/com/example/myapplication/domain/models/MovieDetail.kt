package com.example.myapplication.domain.models

data class MovieDetail(
    val id : Int? = null,
    val name : String? = null,
    val shortDescription : String? = null,
    val url : String? = null,
    val listOfCountry : List<Country>? = null,
    val listOfGenre : List<Genres>? = null
)

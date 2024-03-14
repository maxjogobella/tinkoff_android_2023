package com.example.myapplication.data.storage.models

data class MovieDetailStorageModel(
    val id : Int? = null,
    val name : String? = null,
    val description : String? = null,
    val url : String? = null,
    val listOfCountry : List<CountiresStorageModel>? = null,
    val listOfGenre : List<GenresStorageModel>? = null
)
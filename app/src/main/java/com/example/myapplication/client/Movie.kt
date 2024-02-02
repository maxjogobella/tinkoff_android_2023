package com.example.myapplication.client

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("kinopoiskId")
    val id : Int? = null,
    @SerializedName("nameRu")
    val name : String? = null,
    @SerializedName("posterUrl")
    val url : String? = null,
    @SerializedName("year")
    val year : Int? = null,
    @SerializedName("genres")
    val listOfGenre : List<Genre>
)
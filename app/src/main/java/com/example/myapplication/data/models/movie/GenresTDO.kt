package com.example.myapplication.data.models.movie

import com.google.gson.annotations.SerializedName

data class GenresTDO(
    @SerializedName("genre")
    val name : String? = null
)
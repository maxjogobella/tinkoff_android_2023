package com.example.myapplication.client

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("Genre")
    val genre : String? = null
)
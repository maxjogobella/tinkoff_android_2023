package com.example.myapplication.client

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genre")
    val genre : String? = null
)
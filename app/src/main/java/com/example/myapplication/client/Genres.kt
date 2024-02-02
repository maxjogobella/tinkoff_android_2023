package com.example.myapplication.client

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("genre")
    val name : String? = null
)
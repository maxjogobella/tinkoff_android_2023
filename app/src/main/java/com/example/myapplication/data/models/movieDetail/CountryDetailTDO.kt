package com.example.myapplication.data.models.movieDetail

import com.google.gson.annotations.SerializedName

data class CountryDetailTDO(
    @SerializedName("country")
    val name : String? = null
)
package com.example.myapplication.data.retrofit.model

import com.google.gson.annotations.SerializedName

data class CountryTDO(
    @SerializedName("country")
    val name : String? = null
)
package com.example.myapplication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    val id : Int? = null,
    val name : String? = null,
    val description : String? = null,
    val url : String? = null,
    val listOfCountry : List<Country>? = null,
    val listOfGenre : List<Genres>? = null
) : Parcelable
package com.example.myapplication.data.models.movieDetail

import com.example.myapplication.data.models.movie.GenresTDO
import com.google.gson.annotations.SerializedName

data class MovieDetailTDO(
    @SerializedName("kinopoiskId")
    val id : Int? = null,
    @SerializedName("nameRu")
    val name : String? = null,
    @SerializedName("posterUrl")
    val url : String? = null,
    @SerializedName("genres")
    val listOfGenre : List<GenresTDO>? = null,
    @SerializedName("countries")
    val listOfCountries : List<CountryDetailTDO>? = null,
    @SerializedName("shortDescription")
    val shortDescription : String? = null
)
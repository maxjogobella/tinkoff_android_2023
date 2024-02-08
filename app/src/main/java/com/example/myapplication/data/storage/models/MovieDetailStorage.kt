package com.example.myapplication.data.storage.models

import androidx.room.Entity
import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.models.Genres

@Entity(tableName = "movie_table")
data class MovieDetailStorage(
    val id : Int? = null,
    val name : String? = null,
    val shortDescription : String? = null,
    val url : String? = null,
    val listOfCountry : List<Country>? = null,
    val listOfGenre : List<Genres>? = null
)
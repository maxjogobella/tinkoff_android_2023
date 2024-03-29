package com.example.myapplication.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("detail_table")
data class MovieDetailStorageModel(
    @PrimaryKey val id : Int? = null,
    val name : String? = null,
    val description : String? = null,
    val url : String? = null,
    val listOfCountry : List<CountiresStorageModel>? = null,
    val listOfGenre : List<GenresStorageModel>? = null
)
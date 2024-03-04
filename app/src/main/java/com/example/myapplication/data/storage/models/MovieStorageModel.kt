package com.example.myapplication.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.models.Genres

@Entity("movie_table")
data class MovieStorageModel(
    @PrimaryKey
    val id : Int? = null,
    val name : String? = null,
    val url : String? = null,
    val year : Int? = null,
    val listOfGenre : List<Genres>? = null
)
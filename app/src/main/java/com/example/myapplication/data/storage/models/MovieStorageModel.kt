package com.example.myapplication.data.storage.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.data.storage.GenresListConverter

@Entity("movie_table")
data class MovieStorageModel(
    @PrimaryKey val id : Int? = null,
    val name : String? = null,
    val url : String? = null,
    val year : Int? = null,
    val listOfGenre : List<GenresStorageModel>? = null
)
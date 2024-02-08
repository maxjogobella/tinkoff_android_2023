package com.example.myapplication.domain.models
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val id : Int? = null,
    val name : String? = null,
    val url : String? = null,
    val year : Int? = null,
    val listOfGenre : List<Genres>? = null
) : Parcelable

package com.example.myapplication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name : String? = null
) : Parcelable
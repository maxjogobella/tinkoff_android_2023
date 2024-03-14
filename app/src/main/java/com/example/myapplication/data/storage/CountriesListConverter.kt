package com.example.myapplication.data.storage

import androidx.room.TypeConverter
import com.example.myapplication.data.storage.models.CountiresStorageModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountriesListConverter {
    @TypeConverter
    fun fromString(value : String?) : List<CountiresStorageModel>? {
        val listType = object : TypeToken<List<CountiresStorageModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list : List<CountiresStorageModel>?) : String {
        return Gson().toJson(list)
    }
}
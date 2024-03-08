package com.example.myapplication.data.storage

import androidx.room.TypeConverter
import com.example.myapplication.data.storage.models.GenresStorageModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenresListConverter {
    @TypeConverter
    fun fromString(value : String?) : List<GenresStorageModel>? {
        val listType = object : TypeToken<List<GenresStorageModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list : List<GenresStorageModel>?) : String {
        return Gson().toJson(list)
    }
}
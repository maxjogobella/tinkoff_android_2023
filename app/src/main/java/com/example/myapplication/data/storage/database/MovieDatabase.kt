package com.example.myapplication.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.storage.CountriesListConverter
import com.example.myapplication.data.storage.GenresListConverter
import com.example.myapplication.data.storage.database.dao.MovieDao
import com.example.myapplication.data.storage.models.MovieStorageModel


@Database(entities = [MovieStorageModel::class], version = 9, exportSchema = false)
@TypeConverters(GenresListConverter::class, CountriesListConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "movies.db"
        private var INSTANCE : MovieDatabase? = null
        fun getInstance(context : Context) : MovieDatabase {
            INSTANCE?.let { return it }
            synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
    abstract fun movieDao() : MovieDao
}
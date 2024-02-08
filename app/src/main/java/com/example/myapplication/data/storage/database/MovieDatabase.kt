package com.example.myapplication.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.storage.database.dao.MovieDao
import com.example.myapplication.data.storage.models.MovieDetailStorage

@Database(entities = [MovieDetailStorage::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "movies.db"
        private var db : MovieDatabase? = null
        fun getInstance(context : Context) : MovieDatabase {
            synchronized(this) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    DATABASE_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }
    abstract fun movieDao() : MovieDao
}
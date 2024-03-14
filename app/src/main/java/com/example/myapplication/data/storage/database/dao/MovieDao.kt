package com.example.myapplication.data.storage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.storage.models.MovieDetailStorageModel
import com.example.myapplication.data.storage.models.MovieStorageModel
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getFavoriteMoviesList() : LiveData<List<MovieStorageModel>>

    @Query("SELECT * FROM detail_table WHERE id=:movieId LIMIT 1")
    fun getFavoriteMovie(movieId : Int) : MovieDetailStorageModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(movieStorageModel: MovieStorageModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetailItem(movieDetailStorageModel: MovieDetailStorageModel)

    @Query("DELETE FROM movie_table WHERE id=:movieId")
    suspend fun removeItem(movieId : Int)
}
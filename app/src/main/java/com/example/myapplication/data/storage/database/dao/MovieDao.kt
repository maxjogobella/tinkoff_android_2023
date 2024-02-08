package com.example.myapplication.data.storage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.storage.models.MovieDetailStorage
import io.reactivex.rxjava3.core.Completable

@Dao
interface MovieDao {

    @Insert
    fun addMovie(movieDetailStorage: MovieDetailStorage) : Completable

    @Query("DELETE FROM movie_table WHERE id=:movieId")
    fun deleteMovie(movieId : Int) : Completable

    @Query("SELECT * FROM movie_table")
    fun getFavoriteMovies() : LiveData<MovieDetailStorage>

    @Query("SELECT * FROM movie_table WHERE id=:movieId")
    fun getFavoriteMovie(movieId : Int)

}
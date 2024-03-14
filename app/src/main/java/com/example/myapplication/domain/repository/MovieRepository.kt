package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail

interface MovieRepository {
    fun getFavoriteMovies() : LiveData<List<Movie>>
    suspend fun getMovieDetail(movieId : Int) : MovieDetail
    suspend fun getTopMovies(page : Int) : List<Movie>
    suspend fun addMovie(movie : Movie)
    suspend fun deleteFavoriteMovie(movieId : Int)
    suspend fun getFavoriteMovie(movieId : Int) : MovieDetail?
    fun searchMovieByName(movieName : String) : Movie
}
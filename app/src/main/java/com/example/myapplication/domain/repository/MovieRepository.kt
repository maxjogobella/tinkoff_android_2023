package com.example.myapplication.domain.repository

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail

interface MovieRepository {
    suspend fun getFavoriteMovies() : List<Movie>
    suspend fun getMovieDetail(movieId : Int) : MovieDetail
    suspend fun getTopMovies(page : Int) : List<Movie>
    suspend fun addMovie(movie : Movie)
    suspend fun deleteFavoriteMovie(movieId : Int)
    fun searchMovieByName(movieName : String) : Movie
}
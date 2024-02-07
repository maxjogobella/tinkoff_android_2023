package com.example.myapplication.domain.repository

import com.example.myapplication.domain.models.Movie

interface MovieRepository {
    fun getFavoriteMovies() : List<Movie>
    fun getMovieDetail() : Movie
    fun getTopMovies() : List<Movie>
    fun saveMovie(movie : Movie) : Boolean
    fun deleteMovie(movieId : Int) : Boolean
    fun searchMovieByName(movieName : String) : Boolean

}
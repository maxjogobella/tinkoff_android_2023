package com.example.myapplication.domain.repository

import com.example.myapplication.domain.models.Movie
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getFavoriteMovies() : List<Movie>
    fun getMovieDetail() : Movie
    fun getTopMovies() : Single<List<Movie>>
    fun saveMovie(movie : Movie) : Boolean
    fun deleteFavoriteMovie(movieId : Int) : Boolean
    fun searchMovieByName(movieName : String) : Boolean

}
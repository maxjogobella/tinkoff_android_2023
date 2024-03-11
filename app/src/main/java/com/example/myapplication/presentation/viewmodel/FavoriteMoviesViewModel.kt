package com.example.myapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.usecase.GetFavoriteMoviesUseCase
import com.example.myapplication.domain.usecase.RemoveFavMovieUseCase

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)
    private val getFavoriteMovieUseCase = GetFavoriteMoviesUseCase(repository)
    private val removeFavoriteMoviesUseCase = RemoveFavMovieUseCase(repository)

    val listOfFavoriteMovies : LiveData<List<Movie>>
        get() = getFavoriteMovieUseCase.invoke()
    suspend fun removeMovie(movie : Movie) {
        movie.id?.let { removeFavoriteMoviesUseCase.invoke(it) }
    }

}
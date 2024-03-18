package com.example.myapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.data.storage.database.MovieDatabase
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetFavoriteMovieUseCase
import com.example.myapplication.domain.usecase.GetFavoriteMoviesUseCase
import com.example.myapplication.domain.usecase.RemoveFavMovieUseCase

class FavoriteMoviesViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    private val movieDao = MovieDatabase.getInstance(application).movieDao()
    private val repository = MovieRepositoryImpl(application)
    private val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(repository)
    private val removeFavoriteMoviesUseCase = RemoveFavMovieUseCase(repository)


    val listOfFavoriteMovies : LiveData<List<Movie>>
        get() = getFavoriteMoviesUseCase.invoke()

    suspend fun removeMovie(movie : Movie) {
        movie.isFavorite = false
        movie.id?.let { removeFavoriteMoviesUseCase.invoke(it) }
        movie.id?.let { movieDao.deleteFavoriteDetailMovie(it) }
    }

}
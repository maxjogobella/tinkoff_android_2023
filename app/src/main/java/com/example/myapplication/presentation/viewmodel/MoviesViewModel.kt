package com.example.myapplication.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.usecase.AddFavoriteMovieUseCase
import com.example.myapplication.domain.usecase.GetFavoriteMoviesUseCase
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var page = 1
    private val repository = MovieRepositoryImpl(application)
    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase(repository)
    private val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(repository)

    val _listOfMovies = MutableLiveData<List<Movie>?>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _internetIsNotWorking = MutableLiveData<Boolean>()

    val listOfMovies : LiveData<List<Movie>?>
        get() = _listOfMovies
    private val listOfFavoriteMovies : LiveData<List<Movie>>
        get() = getFavoriteMoviesUseCase.invoke()

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    val internetIsNotWorking : LiveData<Boolean>
        get() = _internetIsNotWorking

    val moviesWithFavorites: LiveData<List<Movie>?> = _listOfMovies.switchMap { movies ->
        listOfFavoriteMovies.map { favorites ->
            combineLatestData(movies, favorites)
        }
    }

    private fun combineLatestData(movies: List<Movie>?, favorites: List<Movie>?): List<Movie>? {
        if (movies == null || favorites == null) return null
        return movies.map { movie ->
            movie.copy(isFavorite = favorites.any { it.id == movie.id })
        }

    }

    init {
        loadTopMovies()
    }

    fun loadTopMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _internetIsNotWorking.value = false

            runCatching {
                val moviesFromResponse = getTopMoviesUseCase.invoke(page)
                updateMovieList(moviesFromResponse)

                page++
            }.onFailure { exception ->
                handleFailure(exception)
            }
            _isLoading.value = false
        }
    }
    private fun updateMovieList(movies: List<Movie>) {
        val moviesFilled = _listOfMovies.value?.toMutableSet()
        if (moviesFilled != null) {
            moviesFilled.addAll(movies)
            _listOfMovies.value = moviesFilled.toList()
        } else {
            _listOfMovies.value = movies
        }
    }

    private fun handleFailure(exception: Throwable) {
        if (exception is UnknownHostException) {
            _internetIsNotWorking.value = true
        }
    }

    suspend fun saveMovie(movie: Movie) {
        addFavoriteMovieUseCase.invoke(movie)
    }
}
package com.example.myapplication.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.usecase.AddFavoriteMovieUseCase
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var page = 1
    private val repository = MovieRepositoryImpl(application)
    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase(repository)


    private val _listOfMovies = MutableLiveData<List<Movie>?>()
    private val _internetIsNotWorking = MutableLiveData<Boolean>()
    val listOfMovies: LiveData<List<Movie>?>
        get() = _listOfMovies

    val internetIsNotWorking : LiveData<Boolean>
        get() = _internetIsNotWorking

    init {
        loadTopMovies()
    }

    fun loadTopMovies() {
        viewModelScope.launch {
            runCatching {
                _internetIsNotWorking.value = false
                val moviesFromResponse = getTopMoviesUseCase.invoke(page)
                val moviesFilled = listOfMovies.value?.toMutableList()

                if (moviesFilled != null) {
                    moviesFilled.addAll(moviesFromResponse)
                    _listOfMovies.value = moviesFilled
                } else {
                    _listOfMovies.value = moviesFromResponse
                }
                page++
            }.onFailure { exception ->
                if (exception is UnknownHostException) {
                    _internetIsNotWorking.value = true
                }
            }
        }
    }

    suspend fun saveMovie(movie: Movie) {
      //  val movieDetail = movie.id?.let { repository.getMovieDetail(movieId = it) }
      //  movieDetail?.let { addFavoriteMovieUseCase.invoke(movie) }
        repository.addMovie(movie)
    }
}
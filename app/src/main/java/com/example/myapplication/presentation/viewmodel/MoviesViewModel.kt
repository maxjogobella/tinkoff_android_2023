package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase
import com.example.myapplication.domain.models.Movie
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private var page = 1
    private val repository = MovieRepositoryImpl()
    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)


    private val _listOfMovies = MutableLiveData<List<Movie>?>()
    val listOfMovies : LiveData<List<Movie>?>
        get() = _listOfMovies

    init {
        loadTopMovies()
    }

     fun loadTopMovies() {
        viewModelScope.launch {
            val moviesFromResponse = getTopMoviesUseCase.invoke(page)
            val moviesFilled = listOfMovies.value?.toMutableList()

            if (moviesFilled != null) {
                moviesFilled.addAll(moviesFromResponse)
                _listOfMovies.value = moviesFilled
            } else {
                _listOfMovies.value = moviesFromResponse
            }
            page++
        }
    }
}
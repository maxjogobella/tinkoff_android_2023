package com.example.myapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetFavoriteMovieUseCase
import com.example.myapplication.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val application: Application,
    private val repository: MovieRepository,
    private val movieId: Int
) : AndroidViewModel(application) {

    private val _movieDetail = MutableLiveData<MovieDetail?>()
    val movieDetail: LiveData<MovieDetail?>
        get() = _movieDetail

    init {
        fetchMovieDetail()
    }

    private fun fetchMovieDetail() {
        viewModelScope.launch {
            val favoriteMovie = repository.getFavoriteMovie(movieId)
            if (favoriteMovie != null) {
                _movieDetail.value = favoriteMovie
            } else {
                _movieDetail.value = repository.getMovieDetail(movieId)
            }
        }
    }
}
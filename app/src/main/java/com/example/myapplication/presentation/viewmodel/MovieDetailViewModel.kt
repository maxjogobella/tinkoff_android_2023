package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieRepository,
    private val movieId : Int
) : ViewModel() {

    private val getMovieDetail = GetMovieDetailUseCase(repository)

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail : LiveData<MovieDetail>
        get() = _movieDetail

    init {
        getMovieDetail(movieId)
    }

    private fun getMovieDetail(movieId : Int) {
        viewModelScope.launch {
            _movieDetail.value = getMovieDetail.invoke(movieId)
        }
    }
}
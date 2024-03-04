package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.repository.MovieRepository

class ViewModelFactory(
    private val repository: MovieRepository,
    private val movieId : Int?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return movieId?.let { MovieDetailViewModel(repository, it) } as T
        } else {
            throw RuntimeException("Unknown viewModel class $modelClass")
        }
    }
}
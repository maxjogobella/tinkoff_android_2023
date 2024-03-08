package com.example.myapplication.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.repository.MovieRepository

class ViewModelFactory(
    private val application : Application,
    private val repository : MovieRepository,
    private val movieId : Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(application, repository, movieId) as T
        } else {
            throw RuntimeException("Unknown viewModel class $modelClass")
        }
    }
}
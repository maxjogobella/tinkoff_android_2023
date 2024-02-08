package com.example.myapplication.presentation.viewmodels.facrotry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.presentation.viewmodels.MainViewModel

class MainViewModelFactory(private val application : Application, private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return MainViewModel(application, repository) as T
    }
}
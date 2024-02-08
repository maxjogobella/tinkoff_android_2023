package com.example.myapplication.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application, private val repository : MovieRepository) : AndroidViewModel(application) {

    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)

    private val _listOfMovies = MutableLiveData<List<Movie>>()
    val listOfMovies : LiveData<List<Movie>> = _listOfMovies

    init {
        getFavoriteMovies()
    }

    fun getFavoriteMovies() {
        getTopMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _listOfMovies.value = it
            }, {

            })
    }

}
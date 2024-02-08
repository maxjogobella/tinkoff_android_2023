package com.example.myapplication.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application, repository : MovieRepository) : AndroidViewModel(application) {

    var currentPage = 1

    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)
    private val compositeDisposable = CompositeDisposable()

    private val _listOfMovies = MutableLiveData<List<Movie>>()
    val listOfMovies : LiveData<List<Movie>> = _listOfMovies

    init {
        getFavoriteMovies(currentPage)
    }

    fun getFavoriteMovies(page : Int) {
       val disposable =  getTopMoviesUseCase.execute(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseListMovies ->
                val currentMovies = _listOfMovies.value?.toMutableList()
                if (currentMovies != null) {
                    responseListMovies.let { currentMovies.addAll(it) }
                    _listOfMovies.value = currentMovies
                } else {
                    _listOfMovies.value = responseListMovies
                }
                currentPage = page
            }, {

            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
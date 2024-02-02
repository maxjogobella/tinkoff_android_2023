package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.client.ApiFactory
import com.example.myapplication.client.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application : Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    val listOfTopMoviesData  = MutableLiveData<List<Movie>> ()
    private var counter : Int = 1

    fun getTopMovies() {
        val disposable = ApiFactory.apiService.getTopMovie(page = counter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                val currentMovies = listOfTopMoviesData.value?.toMutableList()
                if (currentMovies != null) {
                    response.listOfTopMovies?.let { currentMovies.addAll(it) }
                    listOfTopMoviesData.value = currentMovies
                    } else {
                        listOfTopMoviesData.value = response.listOfTopMovies
                    }
                counter++

            }, {
                Log.d("TEST_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
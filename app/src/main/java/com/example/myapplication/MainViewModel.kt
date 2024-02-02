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
    val listOfMovies  = MutableLiveData<List<Movie>> ()

    fun getTopMovies() {
        val disposable = ApiFactory.apiService.getTopMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listOfMovies.value = it.listOfTopMovies
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
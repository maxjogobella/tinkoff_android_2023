package com.example.myapplication.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetMovieDetailByIdUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FragmentDetailViewModel : ViewModel() {

    private val repository : MovieRepository = MovieRepositoryImpl
    private val getMovieDetailByIdUseCase = GetMovieDetailByIdUseCase(repository = repository)
    private val compositeDisposable = CompositeDisposable()

    private val _movie = MutableLiveData<MovieDetail>()
    val movie : LiveData<MovieDetail> = _movie

    fun getMovieById(movieId : Int) {
        val disposable = getMovieDetailByIdUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movie.value = it
            }, {

            })
        compositeDisposable.add(disposable)
    }

}
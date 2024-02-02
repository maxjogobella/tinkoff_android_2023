package com.example.myapplication.client

import io.reactivex.rxjava3.core.Completable
import retrofit2.http.GET

interface ApiService {

    @GET("v2.2/films/top")
    fun getMovieList() : Completable

}
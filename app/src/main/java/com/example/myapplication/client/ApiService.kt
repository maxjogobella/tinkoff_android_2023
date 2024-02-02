package com.example.myapplication.client

import io.reactivex.rxjava3.core.Completable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films")
    fun getMovieList() : Completable

    private companion object {
        private const val API_KEY = "9f33d46c-ae12-4c47-b270-503d7aaecfa2"
    }

}
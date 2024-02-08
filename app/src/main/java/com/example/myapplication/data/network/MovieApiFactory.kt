package com.example.myapplication.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiFactory {

    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService : MovieApiService = retrofit.create(MovieApiService::class.java)


}
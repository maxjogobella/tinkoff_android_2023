package com.example.myapplication.data.network.repository

import com.example.myapplication.data.models.response.TopMovieResponse
import com.example.myapplication.data.network.MovieApiFactory
import io.reactivex.rxjava3.core.Single

object NetworkRepositoryImpl : NetworkRepository {

    private val apiService = MovieApiFactory.apiService
    override fun getTopMovies(page : Int): Single<TopMovieResponse> {
        return apiService.getTopMovie(page = page)
    }
}
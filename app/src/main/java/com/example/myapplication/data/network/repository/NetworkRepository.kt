package com.example.myapplication.data.network.repository

import com.example.myapplication.data.models.MovieTDO
import com.example.myapplication.data.models.response.TopMovieResponse
import io.reactivex.rxjava3.core.Single

    interface NetworkRepository {
        fun getTopMovies() : Single<TopMovieResponse>
    }
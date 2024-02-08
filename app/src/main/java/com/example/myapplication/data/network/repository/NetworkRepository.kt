package com.example.myapplication.data.network.repository

import com.example.myapplication.data.models.movieDetail.MovieDetailTDO
import com.example.myapplication.data.models.response.TopMovieResponse
import io.reactivex.rxjava3.core.Single

    interface NetworkRepository {
        fun getTopMovies(page : Int) : Single<TopMovieResponse>
        fun getMovieById(movieId : Int) : Single<MovieDetailTDO>
    }
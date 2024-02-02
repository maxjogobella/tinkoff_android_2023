package com.example.myapplication.client

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/collections")
    fun getTopMovie(
        @Query(QUERY_PARAM_TYPE) type : String = QUERY_PARAM_POPULAR_MOVIES,
        @Query(QUERY_PARAM_PAGE) page : Int = 1
    ): Single<TopMovieResponse>

    private companion object {
        private const val API_KEY = "9f33d46c-ae12-4c47-b270-503d7aaecfa2"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_POPULAR_MOVIES = "TOP_250_MOVIES"
        private const val QUERY_PARAM_PAGE = "page"
    }

}
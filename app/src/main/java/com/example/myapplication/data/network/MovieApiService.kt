package com.example.myapplication.data.network

import com.example.myapplication.data.models.response.TopMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {
    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/collections")
    fun getTopMovie(
        @Query(QUERY_PARAM_TYPE) type : String = QUERY_PARAM_POPULAR_MOVIES,
        @Query(QUERY_PARAM_PAGE) page : Int
    ): Single<TopMovieResponse>

    private companion object {
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_POPULAR_MOVIES = "TOP_250_MOVIES"
        private const val QUERY_PARAM_PAGE = "page"

        //tinkoff -> e30ffed0-76ab-4dd6-b41f-4c9da2b2735b
    }

}
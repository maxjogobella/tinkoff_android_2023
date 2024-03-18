package com.example.myapplication.data.retrofit

import com.example.myapplication.data.retrofit.model.MovieDetailTDO
import com.example.myapplication.data.retrofit.model.TopMovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/collections")
    suspend fun getTopMovie(
        @Query(QUERY_PARAM_TYPE) type: String = QUERY_PARAM_POPULAR_MOVIES,
        @Query(QUERY_PARAM_PAGE) page: Int
    ): TopMovieResponse

    @Headers("X-API-KEY: $API_KEY")
    @GET("v2.2/films/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: Int
    ): MovieDetailTDO

    private companion object {
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_POPULAR_MOVIES = "TOP_250_MOVIES"
        private const val QUERY_PARAM_PAGE = "page"
        //tinkoff -> e30ffed0-76ab-4dd6-b41f-4c9da2b2735b
        //my -> 22442529-0dd4-4164-b4fc-95f5acf1ba3b
    }
}
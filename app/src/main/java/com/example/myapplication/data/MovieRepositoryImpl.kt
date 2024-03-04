package com.example.myapplication.data

import com.example.myapplication.data.retrofit.ApiFactory
import com.example.myapplication.data.retrofit.model.mapper.MovieTDOMapper
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    private val mapper = MovieTDOMapper()

    override suspend fun getFavoriteMovies(): List<Movie> {
       TODO()
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            val movieDetail = ApiFactory.apiService.getMovieById(movieId)
            return@withContext mapper.mapTDODetailToEntity(movieDetail)
        }
    }


    override suspend fun getTopMovies(page : Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            val tdoModel = ApiFactory.apiService.getTopMovie(page = page).listOfTopMovies
            tdoModel?.map { mapper.mapMovieTDOtoEntity(it) }
                ?: throw RuntimeException("Context getFavoriteMovies == null")
        }
    }

    override fun addMovie(movie: Movie): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteMovie(movieId: Int) {
        TODO("Not yet implemented")
    }

    override fun searchMovieByName(movieName: String): Movie {
        TODO("Not yet implemented")
    }

}
package com.example.myapplication.data

import android.app.Application
import com.example.myapplication.data.retrofit.ApiFactory
import com.example.myapplication.data.retrofit.model.mapper.MovieTDOMapper
import com.example.myapplication.data.storage.database.MovieDatabase
import com.example.myapplication.data.storage.models.mapper.MovieStorageMapper
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(application : Application) : MovieRepository {

    private val movieTDOMapper = MovieTDOMapper()
    private val movieStorageMapper = MovieStorageMapper()
    private val movieDao = MovieDatabase.getInstance(application).movieDao()

    override suspend fun getFavoriteMovies(): List<Movie> {
        val storageModel = movieDao.getFavoriteMoviesList()
        return storageModel.map { movieStorageMapper.mapStorageModelToEntity(it) }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            val movieDetail = ApiFactory.apiService.getMovieById(movieId)
            return@withContext movieTDOMapper.mapTDODetailToEntity(movieDetail)
        }
    }


    override suspend fun getTopMovies(page : Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            val tdoModel = ApiFactory.apiService.getTopMovie(page = page).listOfTopMovies
            tdoModel?.map { movieTDOMapper.mapMovieTDOtoEntity(it) }
                ?: throw RuntimeException("Context getFavoriteMovies == null")
        }
    }

    override suspend fun addMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.addItem(movieStorageMapper.mapEntityToStorageModel(movie))
        }
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        withContext(Dispatchers.IO) {
            movieDao.removeItem(movieId)
        }
    }

    override fun searchMovieByName(movieName: String): Movie {
        TODO("Not yet implemented")
    }

}
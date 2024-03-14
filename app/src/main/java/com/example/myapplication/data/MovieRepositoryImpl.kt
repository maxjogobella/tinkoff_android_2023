package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
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

    override suspend fun getFavoriteMovie(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            val movieStorageModel = movieDao.getFavoriteMovie(movieId)
            if (movieStorageModel != null) {
                return@withContext movieStorageMapper.mapStorageDetailModelToDetailEntity(movieStorageModel)
            } else {
                return@withContext movieTDOMapper.mapTDODetailToEntity(ApiFactory.apiService.getMovieById(movieId))
            }
        }
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> =
        movieDao.getFavoriteMoviesList().map {
            movieStorageMapper.mapListOfStorageModelToListOfEntity(it)
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
        val detailMovie = movie.id?.let { getMovieDetail(movieId = it) }
        detailMovie?.let { movieStorageMapper.mapEntityDetailToDetailModel(it) }
            ?.let { movieDao.addDetailItem(it) }
        movieDao.addItem(movieStorageMapper.mapEntityToStorageModel(movie))
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        movieDao.removeItem(movieId)
    }

    override fun searchMovieByName(movieName: String): Movie {
        TODO("Not yet implemented")
    }

}
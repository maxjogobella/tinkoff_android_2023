package com.example.myapplication.data.repository

import com.example.myapplication.data.models.movie.mapper.MovieMapper
import com.example.myapplication.data.models.movieDetail.mapper.MovieDetailMapper
import com.example.myapplication.data.network.repository.NetworkRepository
import com.example.myapplication.data.network.repository.NetworkRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single

object MovieRepositoryImpl : MovieRepository {

    private val networkRepository : NetworkRepository = NetworkRepositoryImpl
    private val movieMapper : MovieMapper = MovieMapper()
    private val movieDetailMapper : MovieDetailMapper = MovieDetailMapper()

    override fun getFavoriteMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetail(movieId: Int): Single<MovieDetail> {
        return networkRepository.getMovieById(movieId)
            .map { movieDetailTDO -> movieDetailMapper.mapFromMovieDetailTDO(movieDetailTDO) }
    }

    override fun getTopMovies(page : Int): Single<List<Movie>> {
        return networkRepository.getTopMovies(page = page)
            .flatMap {response ->
                val movies = response.listOfTopMovies
                    ?.map { movieTDO -> movieMapper.mapFromMovieTDO(movieTDO) }
                if (movies.isNullOrEmpty()) {
                    Single.error(Throwable("The list is empty"))
                } else {
                    Single.just(movies)
                }
            }
    }

    override fun saveMovie(movie: Movie): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteMovie(movieId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun searchMovieByName(movieName: String): Boolean {
        TODO("Not yet implemented")
    }
}
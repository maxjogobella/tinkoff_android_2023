package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetMovieDetailByIdUseCase(private val repository: MovieRepository) {

    fun execute(movieId : Int) : Single<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }

}
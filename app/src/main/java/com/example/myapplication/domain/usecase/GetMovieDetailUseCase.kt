package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository

class GetMovieDetailUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId : Int) : MovieDetail {
        return repository.getMovieDetail(movieId)
    }
}
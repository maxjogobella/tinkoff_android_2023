package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetMovieDetailByIdUseCase(private val repository: MovieRepository) {

    fun execute(movieId : Int) : Movie {
        return repository.getMovieDetail()
    }

}
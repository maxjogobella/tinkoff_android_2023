package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class SearchMovieByNameUseCase(private val repository: MovieRepository) {

    operator fun invoke(movieName : String) : Movie {
        return repository.searchMovieByName(movieName)
    }
}
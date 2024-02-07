package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class SearchMovieByNameUseCase(private val movieRepository: MovieRepository) {

    fun searchMovie(movie : Movie) : Movie {
        return
    }

}
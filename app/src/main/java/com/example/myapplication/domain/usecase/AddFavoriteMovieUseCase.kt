package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class AddFavoriteMovieUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(movie : Movie) {
        movieRepository.addMovie(movie)
    }
}
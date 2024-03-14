package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class RemoveFavMovieUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId : Int) {
        repository.deleteFavoriteMovie(movieId)
    }
}
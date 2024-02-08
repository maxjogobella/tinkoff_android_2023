package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class DeleteFavoriteMovieByIdUseCase(private val repository: MovieRepository) {
    fun execute(movieId : Int) {
        repository.deleteFavoriteMovie(movieId)
    }
}
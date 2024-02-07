package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class SaveMovieByIdToDbUseCase(private val movieRepository: MovieRepository) {
}
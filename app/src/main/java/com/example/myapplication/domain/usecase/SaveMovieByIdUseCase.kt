package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class SaveMovieByIdUseCase(private val movieRepository: MovieRepository) {
}
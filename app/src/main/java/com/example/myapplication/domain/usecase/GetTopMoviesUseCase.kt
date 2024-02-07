package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class GetTopMoviesUseCase(private val movieRepository: MovieRepository) {
}
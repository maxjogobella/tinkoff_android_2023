package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class SearchMovieByNameUseCase(private val movieRepository: MovieRepository) {
}
package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {

    fun execute() : List<Movie> {

    }


}
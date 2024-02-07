package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetFavoriteMoviesFromDbUseCase(private val movieRepository: MovieRepository) {

    fun execute() : List<Movie> {
        
    }

}
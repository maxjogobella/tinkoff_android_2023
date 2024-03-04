package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetTopMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(page : Int) : List<Movie> {
        return repository.getTopMovies(page)
    }
}
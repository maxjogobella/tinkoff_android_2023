package com.example.myapplication.domain.usecase
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetFavoriteMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke() : List<Movie> {
        return repository.getFavoriteMovies()
    }
}
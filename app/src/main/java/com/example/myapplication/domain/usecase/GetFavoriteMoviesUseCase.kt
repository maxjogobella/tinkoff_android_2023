package com.example.myapplication.domain.usecase
import androidx.lifecycle.LiveData
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class GetFavoriteMoviesUseCase(private val repository: MovieRepository) {

     operator fun invoke() : LiveData<List<Movie>> {
        return repository.getFavoriteMovies()
    }
}
package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class SaveMovieUseCase(private val repository: MovieRepository) {

    fun execute(movie : Movie) {
        repository.saveMovie(movie)
    }

}
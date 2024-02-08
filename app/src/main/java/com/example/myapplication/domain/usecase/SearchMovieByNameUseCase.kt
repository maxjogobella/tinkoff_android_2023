package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class SearchMovieByNameUseCase(private val repository: MovieRepository) {

    fun execute(movieName : String) : Boolean {
        repository.searchMovieByName(movieName)
        return true
    }

}
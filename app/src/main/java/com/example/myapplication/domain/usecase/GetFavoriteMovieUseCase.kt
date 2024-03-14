package com.example.myapplication.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository

class GetFavoriteMovieUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId : Int) : MovieDetail? {
        return repository.getFavoriteMovie(movieId)
    }

}
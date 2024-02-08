package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetTopMoviesUseCase(private val repository: MovieRepository) {

    fun execute(page : Int) : Single<List<Movie>> {
        return repository.getTopMovies(page = page)
    }

}
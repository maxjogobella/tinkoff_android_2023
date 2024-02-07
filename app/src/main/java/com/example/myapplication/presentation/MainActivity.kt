package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetMovieDetailByIdUseCase
import com.example.myapplication.domain.usecase.GetTopMoviesUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var movieList : List<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository : MovieRepository = MovieRepositoryImpl

        movieList = GetMovieDetailByIdUseCase(repository)

    }
}
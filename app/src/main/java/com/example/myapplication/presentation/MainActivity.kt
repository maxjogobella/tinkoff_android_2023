package com.example.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.adapter.MovieListAdapter
import com.example.myapplication.presentation.viewmodels.MainViewModel
import com.example.myapplication.presentation.viewmodels.facrotry.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(this.application, MovieRepositoryImpl))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapterMovie = MovieListAdapter()

        val rv = binding.recycleViewMovies
        rv.adapter = adapterMovie


        viewModel.listOfMovies.observe(this) {
            adapterMovie.submitList(it)
        }

    }
}
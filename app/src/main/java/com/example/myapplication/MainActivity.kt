package com.example.myapplication

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.client.Movie
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
        
    private val viewModel: MainViewModel by lazy {
        MainViewModel(Application())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = MovieAdapter()
        binding.recycleViewMovies.adapter = adapter

        viewModel.getTopMovies()

       adapter.onReachEndListener = object : MovieAdapter.OnReachEndListener {
           override fun onEndReach() {
               viewModel.getTopMovies()
           }
       }

        viewModel.listOfTopMoviesData.observe(this) { list ->
            adapter.movieList = list
        }
    }
}
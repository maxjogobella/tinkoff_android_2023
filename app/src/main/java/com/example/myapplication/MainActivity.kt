package com.example.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.client.ApiFactory
import com.example.myapplication.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

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

        viewModel.listOfMovies.observe(this) { list ->
            adapter.movieList = list
        }
    }
}
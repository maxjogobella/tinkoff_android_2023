package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.client.Movie
import com.example.myapplication.databinding.MovieItemBinding
import java.util.Locale

class MovieAdapter() : Adapter<MovieAdapter.MovieViewHolder>() {

    var movieList : List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged() // чуть позже измени логику, т.к он не эффективный
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemBinding.inflate(
            LayoutInflater.from(
                parent.context),
                parent,
                false)
        return MovieViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        with(holder) {
            movie.url?.let { ImageLoader.load(ivMainPoster, it) }
            movie.name?.let { tvMainTitle.text = it }
            movie.year?.let { tvMainYear.text = "($it)" }
            movie.listOfGenre?.let { tvMainGenre.text = it[0].name?.replaceFirstChar {it.uppercase(
                Locale.ROOT) }}
        }
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(binding : MovieItemBinding) : ViewHolder(binding.root) {
        val ivMainPoster = binding.ivMainPoster
        val tvMainTitle = binding.tvMainTitle
        val tvMainYear = binding.tvMainYear
        val ivMainStar = binding.ivMainStar
        val tvMainGenre = binding.tvMainGenre
    }
}
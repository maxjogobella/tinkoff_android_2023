package com.example.myapplication.presentation.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.MovieItemBinding

class MovieViewHolder(binding : MovieItemBinding) : ViewHolder(binding.root) {

    val ivMainPoster = binding.ivMainPoster
    val tvMainTitle = binding.tvMainTitle
    val tvMainGenre = binding.tvMainGenre
    val tvMainYear = binding.tvMainYear
    val ivMainStar = binding.ivMainStar
}
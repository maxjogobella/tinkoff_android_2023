package com.example.myapplication.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.MovieItemBinding
import com.example.myapplication.domain.models.Movie

class MovieListAdapter : ListAdapter<Movie, MovieListViewHolder>(MovieListAdapterCallback()) {

    var onReachEndListener : (() -> Unit)? = null
    private var counter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        Log.d("MovieListAdapter", "OnCreateViewHolder : ${counter++}")
        val inflater = LayoutInflater.from(parent.context)
        val view = MovieItemBinding.inflate(inflater, parent, false)
        return MovieListViewHolder((view))
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movieItem = getItem(position)
        with(holder) {
            tvMainGenre.text = movieItem.listOfGenre?.get(0)?.name?.replaceFirstChar { it.uppercase() }
            tvMainYear.text = movieItem.year.toString()
            tvMainTitle.text = movieItem.name
        }

        if (currentList.size -6 <= position) {
            onReachEndListener?.invoke()
        }

        Glide.with(holder.itemView)
            .load(movieItem.url)
            .into(holder.ivMainPoster)
    }

    companion object {
        const val MAX_POOL_SIZE = 3
    }
}
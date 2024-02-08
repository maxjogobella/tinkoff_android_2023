package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.MovieItemBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.presentation.GlideLoader

class MovieListAdapter : ListAdapter<Movie, MovieListViewHolder>(MovieListAdapterCallback()) {

    var onReachEndListener : (() -> Unit)? = null
    var onMovieClickListener : ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
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
            GlideLoader.execute(view = itemView, url = movieItem.url, imageView = ivMainPoster)
        }

        if (currentList.size - 6 <= position) {
            onReachEndListener?.invoke()
        }

        holder.itemView.setOnClickListener {
            onMovieClickListener?.invoke(movieItem)
        }
    }

    companion object {
        const val MAX_POOL_SIZE = 3
    }
}
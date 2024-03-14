package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.MovieItemBinding
import com.example.myapplication.domain.models.Movie

class TopMoviesAdapter : ListAdapter<Movie, MovieViewHolder>(MovieListAdapterCallBack()) {

    var onEndReachListener : (() -> Unit)? = null
    var onMovieClickListener : ((Movie) -> Unit)? = null
    var onMovieClickLongListener : ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        with(holder) {
            tvMainGenre.text = movieItem.listOfGenre?.get(0)?.name?.replaceFirstChar { it.uppercase() }
            tvMainYear.text = movieItem.year.toString()
            tvMainTitle.text = movieItem.name
            GlideLoader.execute(
                context = itemView.context,
                url = movieItem.url,
                imageView = ivMainPoster
            )
        }

        if (position >= currentList.size - 6) {
            onEndReachListener?.invoke()
        }

        holder.itemView.setOnClickListener {
            onMovieClickListener?.invoke(movieItem)
        }

        holder.itemView.setOnLongClickListener {
            onMovieClickLongListener?.invoke(movieItem)
            return@setOnLongClickListener true
        }
    }

}
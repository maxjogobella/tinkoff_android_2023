package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MovieItemBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.repository.MovieRepository

class TopMoviesAdapter(
    private val movieRepository: MovieRepository
) : ListAdapter<Movie, MovieViewHolder>(MovieListAdapterCallBack()) {

    var onClickListener : OnClickListener? = null
    var onItemClickListener: ((Int) -> Unit)? = null
    var onEndReachListener: (() -> Unit)? = null
    var onMovieClickListener: ((Movie) -> Unit)? = null
    var onMovieClickLongListener: ((Movie) -> Unit)? = null

    fun setFilteredList(mList : List<Movie>) {
        submitList(mList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        with(holder) {
            tvMainGenre.text =
                movieItem.listOfGenre?.get(0)?.name?.replaceFirstChar { it.uppercase() }
            tvMainYear.text = movieItem.year.toString()
            tvMainTitle.text = movieItem.name

            if (movieItem.isFavorite == true) {
                ivMainStar.visibility = View.VISIBLE
            } else {
                ivMainStar.visibility = View.GONE
            }

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
            if (position != RecyclerView.NO_POSITION) {
                onClickListener?.onClick(position)
            }
        }

        holder.itemView.setOnClickListener {
            onMovieClickListener?.invoke(movieItem)
        }

        holder.itemView.setOnLongClickListener {
            onMovieClickLongListener?.invoke(movieItem)
            return@setOnLongClickListener true
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

}
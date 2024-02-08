package com.example.myapplication.presentation.fragmenst

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.databinding.FragmentTopMovieBinding
import com.example.myapplication.presentation.adapter.MovieListAdapter
import com.example.myapplication.presentation.viewmodels.MainViewModel
import com.example.myapplication.presentation.viewmodels.facrotry.MainViewModelFactory

class MovieTopFragment : Fragment() {

    private lateinit var binding : FragmentTopMovieBinding

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application,
            MovieRepositoryImpl))[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.yellow_tinkoff_trans))

        val adapterMovie = MovieListAdapter()

        val rv = binding.recycleViewMovies
        rv.adapter = adapterMovie
        rv.recycledViewPool.setMaxRecycledViews(
            R.layout.movie_item,
            MovieListAdapter.MAX_POOL_SIZE
        )

        adapterMovie.onReachEndListener = {
            viewModel.getFavoriteMovies(viewModel.currentPage + 1)
        }

        adapterMovie.onMovieClickListener = {movie ->
            val fragment = MovieDetailFragment.newInstance(movie)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }


        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            adapterMovie.submitList(it)
        }
    }


    companion object {
        fun newInstance() : MovieTopFragment {
            return MovieTopFragment()
        }
    }
}
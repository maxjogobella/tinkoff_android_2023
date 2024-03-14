package com.example.myapplication.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.databinding.MovieDetailFragmentBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.presentation.adapter.GlideLoader
import com.example.myapplication.presentation.viewmodel.MovieDetailViewModel
import com.example.myapplication.presentation.viewmodel.ViewModelFactory

class MovieDetailFragment : Fragment() {

    private lateinit var movieItem: Movie

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding: MovieDetailFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment MovieDetailFragment == null")

    private lateinit var viewModelFactory : ViewModelFactory
    private lateinit var viewModel : MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        viewModelFactory =  ViewModelFactory(
            application = requireActivity().application,
            repository = MovieRepositoryImpl(requireActivity().application),
            movieId = movieItem.id ?: throw RuntimeException("MovieId in MovieDetailFragment == null")
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            val fragment = MoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }

        viewModel.movieDetail.observe(viewLifecycleOwner) {movieDetail ->
            with(binding) {
                GlideLoader.execute(requireContext(), movieDetail?.url, tvDetailPoster)
                tvDetailTitle.text = movieDetail?.name
                tvDetailDescription.text = movieDetail?.description
                tvDetailGenre.text = movieDetail?.listOfGenre
                    ?.mapNotNull { it.name }?.joinToString(", ")
                    ?: "No genres available"
                tvDetailCountry.text = movieDetail?.listOfCountry
                    ?.mapNotNull { it.name }?.joinToString(", ")
                    ?: "No countries available"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Movie>(EXTRA_MOVIE)?.let {
            movieItem = it
        }
    }

    companion object {
        private const val EXTRA_MOVIE = "extraMovie"
        fun newInstance(movie: Movie): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }
            }
        }
    }
}
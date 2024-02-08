package com.example.myapplication.presentation.fragmenst

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDetailMovieBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail
import com.example.myapplication.presentation.viewmodels.FragmentDetailViewModel

class MovieDetailFragment : Fragment() {

    private val viewModel : FragmentDetailViewModel by lazy {
        ViewModelProvider(this)[FragmentDetailViewModel::class.java]
    }
    private lateinit var binding : FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()
        val movie = args.getParcelable<Movie>(EXTRA_MOVIE)
        Log.d("MovieDetailFragment", movie.toString())

        viewModel.getMovieById(movie?.id ?: 0)

        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        viewModel.movie.observe(viewLifecycleOwner) {

            Glide.with(requireContext())
                .load(it.url)
                .into(binding.tvDetailPoster)

            binding.tvDetailTitle.text = it.name
            binding.tvDetailDescription.text = it.shortDescription
            binding.tvDetailGenre.text = it.listOfGenre?.get(0)?.name
            binding.tvDetailCountry.text = it.listOfCountry?.get(0)?.name
        }
    }

    companion object {

        private const val EXTRA_MOVIE = "movie"

        fun newInstance(movie : Movie) : MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }
            }
        }
    }
}
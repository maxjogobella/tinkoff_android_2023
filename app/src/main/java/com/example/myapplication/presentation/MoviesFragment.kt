package com.example.myapplication.presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.MoviesFragmentBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.presentation.adapter.TopMoviesAdapter
import com.example.myapplication.presentation.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    private val viewModel : MoviesViewModel by lazy {
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }
    private var _binding : MoviesFragmentBinding? = null
    private val binding : MoviesFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment MoviesFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recycleViewMovies

        val movieAdapter = TopMoviesAdapter()
        recyclerView.adapter = movieAdapter

        binding.buttonFavorite.setOnClickListener {
            val fragment = FavoriteMoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }

        movieAdapter.onEndReachListener = {
            viewModel.loadTopMovies()
        }

        movieAdapter.onMovieClickListener = { movie: Movie ->
            val fragment = MovieDetailFragment.newInstance(movie)
            requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .commit()

        }

        viewModel.listOfMovies.observe(viewLifecycleOwner) {listOfTopMovies ->
            movieAdapter.submitList(listOfTopMovies)
        }
    }

    companion object {
        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
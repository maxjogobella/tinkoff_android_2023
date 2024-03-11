package com.example.myapplication.presentation
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.MoviesFragmentBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.presentation.adapter.TopMoviesAdapter
import com.example.myapplication.presentation.viewmodel.MoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesFragment : Fragment() {
1й
    private val viewModel : MoviesViewModel by lazy {
        Log.d("MoviesFragment", "Creating new ViewModel")
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    private lateinit var moviesAdapter: TopMoviesAdapter
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
        moviesAdapter = TopMoviesAdapter()
        recyclerView.adapter = moviesAdapter

        binding.buttonFavorite.setOnClickListener {
            val fragment = FavoriteMoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }

        viewModel.internetIsNotWorking.observe(viewLifecycleOwner) {isNotWorking ->
            if (isNotWorking) {
                binding.clError.visibility = View.VISIBLE
            } else {
                binding.clError.visibility = View.INVISIBLE
            }
        }

        moviesAdapter.onEndReachListener = {
            viewModel.loadTopMovies()
        }

        moviesAdapter.onMovieClickLongListener = {movie ->
            val coroutine = CoroutineScope(Dispatchers.IO)
            coroutine.launch {
                viewModel.saveMovie(movie)
            }
            Toast.makeText(requireContext(), "Фильм добавление в избранное", Toast.LENGTH_SHORT).show()
        }


        moviesAdapter.onMovieClickListener = { movie: Movie ->
            val fragment = MovieDetailFragment.newInstance(movie)
            requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .commit()

        }

        viewModel.listOfMovies.observe(viewLifecycleOwner) {listOfTopMovies ->
            moviesAdapter.submitList(listOfTopMovies)
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
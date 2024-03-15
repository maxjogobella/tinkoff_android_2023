package com.example.myapplication.presentation.fragment

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

class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by lazy {
        Log.d("MoviesFragment", "Creating new ViewModel")
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }
    private lateinit var moviesAdapter: TopMoviesAdapter

    private var _binding: MoviesFragmentBinding? = null
    private val binding: MoviesFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment MoviesFragment == null")


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

        setUpAdapter()
        observeViewModel()
        launchOnFavoriteMoviesFragment()
        setAdapterListenerOnLongListener()
        setAdapterListenerOnListener()
        setAdapterListenerOnEndListener()


        binding.buttonTryAgain.setOnClickListener{
            viewModel.loadTopMovies()
        }

    }

    private fun observeViewModel() {

        viewModel.listOfMovies.observe(viewLifecycleOwner) { listOfTopMovies ->
            moviesAdapter.submitList(listOfTopMovies)
        }

        viewModel.internetIsNotWorking.observe(viewLifecycleOwner) { isNotWorking ->
            with(binding) {
                if (isNotWorking) {
                    clError.visibility = View.VISIBLE
                } else {
                    clError.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setAdapterListenerOnEndListener() {
        moviesAdapter.onEndReachListener = {
            viewModel.loadTopMovies()
        }
    }

    private fun setAdapterListenerOnLongListener() {
        moviesAdapter.onMovieClickLongListener = { movie ->
            val coroutine = CoroutineScope(Dispatchers.IO)
            coroutine.launch {
                viewModel.saveMovie(movie)
            }
            Toast.makeText(requireContext(), "Фильм успешно добавлен", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapterListenerOnListener() {
        moviesAdapter.onMovieClickListener = { movie: Movie ->
            val fragment = MovieDetailFragment.newInstance(movie)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    private fun launchOnFavoriteMoviesFragment() {
        binding.buttonFavorite.setOnClickListener {
            val fragment = FavoriteMoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    private fun setUpAdapter() {
        moviesAdapter = TopMoviesAdapter()
        binding.recycleViewMovies.adapter = moviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}
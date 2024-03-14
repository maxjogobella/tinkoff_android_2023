package com.example.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FavoriteMoviesFragmentBinding
import com.example.myapplication.presentation.adapter.TopMoviesAdapter
import com.example.myapplication.presentation.viewmodel.FavoriteMoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteMoviesFragment : Fragment() {

    private val viewModel : FavoriteMoviesViewModel by lazy {
        ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
    }

    private var _binding : FavoriteMoviesFragmentBinding? = null
    private val binding : FavoriteMoviesFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment FavoriteMoviesFragment == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recycleView
        val adapterMv = TopMoviesAdapter()
        recyclerView.adapter = adapterMv

        viewModel.listOfFavoriteMovies.observe(viewLifecycleOwner) {
            adapterMv.submitList(it)
        }

        adapterMv.onMovieClickLongListener = {
            val corountine = CoroutineScope(Dispatchers.IO)
            corountine.launch {
                viewModel.removeMovie(it)
            }
        }

        adapterMv.onMovieClickListener = {
            val fragment = MovieDetailFragment.newInstance(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }

        binding.button2.setOnClickListener {
            val fragment = MoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {
        fun newInstance() : FavoriteMoviesFragment {
            return FavoriteMoviesFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.MovieRepositoryImpl
import com.example.myapplication.databinding.FavoriteMoviesFragmentBinding
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.presentation.adapter.TopMoviesAdapter
import com.example.myapplication.presentation.viewmodel.FavoriteMoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class FavoriteMoviesFragment : Fragment() {

    private val viewModel: FavoriteMoviesViewModel by lazy {
        ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
    }

    private lateinit var favoriteAdapter: TopMoviesAdapter

    private var _binding: FavoriteMoviesFragmentBinding? = null
    private val binding: FavoriteMoviesFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment FavoriteMoviesFragment == null")

    private fun filterList(query: String?) {
        if (query != null) {
            viewModel.listOfFavoriteMovies.observe(viewLifecycleOwner) { movies ->
                val filteredList = ArrayList<Movie>()
                for (i in movies ?: emptyList()) {
                    if (i.name?.lowercase(Locale.getDefault())
                            ?.contains(query.lowercase(Locale.getDefault())) == true
                    ) {
                        filteredList.add(i)
                    }
                }
                if (filteredList.isEmpty()) {
                    binding.clSearchErrorFavorite.visibility = View.VISIBLE
                    binding.clMainFavorite.visibility = View.GONE
                } else {
                    binding.clMainFavorite.visibility = View.VISIBLE
                    binding.clSearchErrorFavorite.visibility = View.GONE
                    favoriteAdapter.setFilteredList(filteredList)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recycleView
        favoriteAdapter = TopMoviesAdapter(MovieRepositoryImpl(requireActivity().application))
        recyclerView.adapter = favoriteAdapter


        viewModel.listOfFavoriteMovies.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }

        favoriteAdapter.onMovieClickLongListener = {
            val corountine = CoroutineScope(Dispatchers.IO)
            corountine.launch {
                viewModel.removeMovie(it)
            }
        }

        favoriteAdapter.onMovieClickListener = {
            val fragment = MovieDetailFragment.newInstance(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.button2.setOnClickListener {
            val fragment = MoviesFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.searchViewFavorite.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        binding.searchViewFavorite.setOnSearchClickListener {
            binding.textView.visibility = View.INVISIBLE
        }

        binding.searchViewFavorite.setOnCloseListener {
            binding.textView.visibility = View.VISIBLE
            return@setOnCloseListener false
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
        fun newInstance(): FavoriteMoviesFragment {
            return FavoriteMoviesFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

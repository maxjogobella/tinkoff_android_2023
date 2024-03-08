package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FavoriteMoviesFragmentBinding

class FavoriteMoviesFragment : Fragment() {

    private var _binding : FavoriteMoviesFragmentBinding? = null
    private val binding : FavoriteMoviesFragmentBinding
        get() = _binding ?: throw RuntimeException("Fragment FavoriteMoviesFragment == null")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

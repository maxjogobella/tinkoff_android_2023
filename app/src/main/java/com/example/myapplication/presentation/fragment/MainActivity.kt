package com.example.myapplication.presentation.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchOnMoviesFragment()
    }


    private fun launchOnMoviesFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (fragment !is MoviesFragment) {
            fragment = MoviesFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}
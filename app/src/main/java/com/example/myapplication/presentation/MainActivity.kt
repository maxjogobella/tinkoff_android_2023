package com.example.myapplication.presentation

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
        val fragment = MoviesFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, fragment)
            .commit()
    }
}
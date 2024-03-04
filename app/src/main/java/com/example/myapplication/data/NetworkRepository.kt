package com.example.myapplication.data

import com.example.myapplication.data.retrofit.model.MovieTDO

interface NetworkRepository {

    fun getTopMovies(page : Int) : List<MovieTDO>
}
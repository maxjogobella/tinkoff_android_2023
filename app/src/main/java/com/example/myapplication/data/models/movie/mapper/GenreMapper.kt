package com.example.myapplication.data.models.movie.mapper

import com.example.myapplication.data.models.movie.GenresTDO
import com.example.myapplication.domain.models.Genres

class GenreMapper {
    fun mapFromGenreTDO(genresTDO: GenresTDO) : Genres {
        return Genres(
            name = genresTDO.name
        )
    }
}
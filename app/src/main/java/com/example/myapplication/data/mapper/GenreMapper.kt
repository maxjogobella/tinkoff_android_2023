package com.example.myapplication.data.mapper

import com.example.myapplication.data.models.GenresTDO
import com.example.myapplication.domain.models.Genres

class GenreMapper {
    fun mapFromGenreTDO(genresTDO: GenresTDO) : Genres {
        return Genres(
            name = genresTDO.name
        )
    }
}
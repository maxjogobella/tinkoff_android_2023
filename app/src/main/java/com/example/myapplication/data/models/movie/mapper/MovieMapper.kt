package com.example.myapplication.data.models.movie.mapper

import com.example.myapplication.data.models.movie.MovieTDO
import com.example.myapplication.domain.models.Movie

class MovieMapper {

    private val genreMapper: GenreMapper = GenreMapper()
    fun mapFromMovieTDO(movieTDO: MovieTDO): Movie {
        return Movie(
            id = movieTDO.id,
            name = movieTDO.name,
            url = movieTDO.url,
            year = movieTDO.year,
            listOfGenre = movieTDO.listOfGenre?.map { genresTDO ->
                genreMapper.mapFromGenreTDO(
                    genresTDO
                )
            }
        )
    }
}
package com.example.myapplication.data.models.movieDetail.mapper

import com.example.myapplication.data.models.movie.mapper.GenreMapper
import com.example.myapplication.data.models.movieDetail.MovieDetailTDO
import com.example.myapplication.domain.models.MovieDetail

class MovieDetailMapper {

    private val genreMapper: GenreMapper = GenreMapper()
    private val countryDetailMapper : CountryDetailMapper = CountryDetailMapper()
    fun mapFromMovieDetailTDO(movieDetailTDO: MovieDetailTDO) : MovieDetail {
        return MovieDetail(
            id = movieDetailTDO.id,
            name = movieDetailTDO.name,
            shortDescription = movieDetailTDO.shortDescription,
            url = movieDetailTDO.url,
            listOfGenre = movieDetailTDO.listOfGenre?.map { genresTDO ->
                genreMapper.mapFromGenreTDO(
                    genresTDO
                )
            },
            listOfCountry = movieDetailTDO.listOfCountries?.map { countiesTDO ->
                countryDetailMapper.mapFromCountryDetailTDO(
                    countiesTDO
                )
            }
        )
    }
}



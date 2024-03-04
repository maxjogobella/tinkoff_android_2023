package com.example.myapplication.data.retrofit.model.mapper

import com.example.myapplication.data.retrofit.model.CountryTDO
import com.example.myapplication.data.retrofit.model.GenresTDO
import com.example.myapplication.data.retrofit.model.MovieDetailTDO
import com.example.myapplication.data.retrofit.model.MovieTDO
import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.models.Genres
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail

class MovieTDOMapper {

    fun mapMovieTDOtoEntity(movieTDO: MovieTDO): Movie = Movie(
        id = movieTDO.id,
        name = movieTDO.name,
        url = movieTDO.url,
        year = movieTDO.year,
        listOfGenre = mapListOfGenresTDOtoListOfEntity(movieTDO.listOfGenre)
    )

    private fun mapGenresTDOToEntity(genresTDO: GenresTDO): Genres = Genres(
        name = genresTDO.name
    )

    private fun mapCountriesTDOtoEntity(countryTDO: CountryTDO): Country = Country(
        name = countryTDO.name
    )

    private fun mapListOfGenresTDOtoListOfEntity(list: List<GenresTDO>?): List<Genres>? =
        list?.map {
            mapGenresTDOToEntity(it)
        }

    private fun mapListOfCounriesTDOtoListOfEntity(list: List<CountryTDO>?): List<Country>? =
        list?.map {
            mapCountriesTDOtoEntity(it)
        }

    fun mapTDODetailToEntity(movieDetailTDO: MovieDetailTDO): MovieDetail = MovieDetail(
        id = movieDetailTDO.id,
        name = movieDetailTDO.name,
        url = movieDetailTDO.url,
        listOfGenre = mapListOfGenresTDOtoListOfEntity(movieDetailTDO.listOfGenre),
        listOfCountry = mapListOfCounriesTDOtoListOfEntity(movieDetailTDO.listOfCountries),
        description = movieDetailTDO.description
    )
}
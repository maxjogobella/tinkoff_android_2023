package com.example.myapplication.data.storage.models.mapper

import com.example.myapplication.data.storage.models.CountiresStorageModel
import com.example.myapplication.data.storage.models.GenresStorageModel
import com.example.myapplication.data.storage.models.MovieStorageModel
import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.models.Genres
import com.example.myapplication.domain.models.Movie
import com.example.myapplication.domain.models.MovieDetail

class MovieStorageMapper {

    // Mapping functions for Movie
    fun mapStorageModelToEntity(movieStorageModel: MovieStorageModel): Movie {
        return Movie(
            id = movieStorageModel.id,
            name = movieStorageModel.name,
            url = movieStorageModel.url,
            listOfGenre = movieStorageModel.listOfGenre?.let { mapListOfGenresStorageToEntity(it) }
        )
    }

    fun mapMovieStorageToEntityMovie(movieStorageModel: MovieStorageModel) : Movie {
        return Movie(
            id = movieStorageModel.id,
            name = movieStorageModel.name,
            url = movieStorageModel.url,
            listOfGenre = movieStorageModel.listOfGenre?.let { mapListOfGenresStorageToEntity(it) }
        )
    }

    fun mapListOfMoviesStorageToListOfEntityMovies(list : List<MovieStorageModel>) : List<Movie> {
        return list.map { mapMovieStorageToEntityMovie(it) }
    }

    fun mapEntityToStorageModel(movie: Movie): MovieStorageModel {
        return MovieStorageModel(
            id = movie.id,
            name = movie.name,
            url = movie.url,
            listOfGenre = movie.listOfGenre?.let { mapListOfEntityGenresToListOfStorageGenres(it) }
        )
    }

    // Mapping function for MovieDetail
    fun mapStorageDetailModelToDetailEntity(movieStorageModel: MovieStorageModel): MovieDetail {
        return MovieDetail(
            id = movieStorageModel.id,
            name = movieStorageModel.name,
            description = movieStorageModel.description,
            url = movieStorageModel.url,
            listOfCountry = movieStorageModel.listOfCountry?.let { mapListOfCountriesStorageToEntity(it) },
            listOfGenre = movieStorageModel.listOfGenre?.let { mapListOfGenresStorageToEntity(it) }
        )
    }

    // Mapping functions for lists
    fun mapListOfStorageModelToListOfEntity(list: List<MovieStorageModel>): List<Movie> {
        return list.map { mapStorageModelToEntity(it) }
    }

    fun mapListOfGenresStorageToEntity(list: List<GenresStorageModel>): List<Genres> {
        return list.map { mapStorageGenresToEntity(it) }
    }

    fun mapListOfEntityGenresToListOfStorageGenres(list: List<Genres>): List<GenresStorageModel> {
        return list.map { mapEntityGenresToStorage(it) }
    }

    fun mapListOfCountriesStorageToEntity(list: List<CountiresStorageModel>): List<Country> {
        return list.map { mapStorageCountriesToEntity(it) }
    }

    // Mapping functions for Genres and Country
    private fun mapStorageGenresToEntity(genresStorageModel: GenresStorageModel): Genres {
        return Genres(name = genresStorageModel.name)
    }

    private fun mapEntityGenresToStorage(genres: Genres): GenresStorageModel {
        return GenresStorageModel(name = genres.name)
    }

    private fun mapStorageCountriesToEntity(countriesStorageModel: CountiresStorageModel): Country {
        return Country(name = countriesStorageModel.name)
    }
}
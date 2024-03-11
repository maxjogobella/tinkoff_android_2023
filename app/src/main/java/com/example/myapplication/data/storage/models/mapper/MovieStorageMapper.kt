package com.example.myapplication.data.storage.models.mapper

import com.example.myapplication.data.storage.models.GenresStorageModel
import com.example.myapplication.data.storage.models.MovieStorageModel
import com.example.myapplication.domain.models.Genres
import com.example.myapplication.domain.models.Movie

class MovieStorageMapper {
    fun mapStorageModelToEntity(movieStorageModel: MovieStorageModel): Movie = Movie(
        id = movieStorageModel.id,
        name = movieStorageModel.name,
        url = movieStorageModel.url,
        year = movieStorageModel.year,
        listOfGenre = movieStorageModel.listOfGenre?.let { mapListOfGenresStorageToEntity(it) }
    )

    fun mapEntityToStorageModel(movie: Movie): MovieStorageModel = MovieStorageModel(
        id = movie.id,
        name = movie.name,
        url = movie.url,
        year = movie.year,
        listOfGenre = movie.listOfGenre?.let { mapListOfEntityGenresToListOfStorageGenres(it) }
    )

    fun mapListOfStorageModelToListOfEntity(list: List<MovieStorageModel>) = list.map {
        mapStorageModelToEntity(it)
    }
}

private fun mapStorageGenresToEntity(genresStorageModel: GenresStorageModel): Genres = Genres(
    name = genresStorageModel.name
)

private fun mapEntityGenresToStorage(genres: Genres): GenresStorageModel = GenresStorageModel(
    name = genres.name
)

fun mapListOfGenresStorageToEntity(list: List<GenresStorageModel>): List<Genres> =
    list.map {
        mapStorageGenresToEntity(it)
    }

private fun mapListOfEntityGenresToListOfStorageGenres(list: List<Genres>): List<GenresStorageModel> =
    list.map {
        mapEntityGenresToStorage(it)
    }



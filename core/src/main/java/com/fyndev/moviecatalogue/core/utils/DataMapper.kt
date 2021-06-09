package com.fyndev.moviecatalogue.core.utils

import com.fyndev.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.core.domain.model.TvShow

object DataMapper {
    /*
     * List
     */
    fun mapResponseToMovieEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                release_date = it.release_date,
                vote_average = it.vote_average,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapResponseToTvShowEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                name = it.name,
                overview = it.overview,
                first_air_date = it.first_air_date,
                vote_average = it.vote_average,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path
            )
            tvShows.add(tvShow)
        }
        return tvShows
    }

    fun mapEntitiesToMovieDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                release_date = it.release_date,
                vote_average = it.vote_average,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapEntitiesToTvShowDomain(input: List<TvShowEntity>): List<TvShow> {
        return input.map {
            TvShow(
                id = it.id,
                name = it.name,
                overview = it.overview,
                first_air_date = it.first_air_date,
                vote_average = it.vote_average,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                isFavorite = it.isFavorite
            )
        }
    }

    /*
     * Detail
     */
    fun mapEntitiesToDetailMovieDomain(input: MovieEntity): Movie {
        return Movie(
            id = input.id,
            title = input.title,
            overview = input.overview,
            release_date = input.release_date,
            vote_average = input.vote_average,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
        )
    }

    fun mapEntitiesToDetailTvSHowDomain(input: TvShowEntity): TvShow {
        return TvShow(
            id = input.id,
            name = input.name,
            overview = input.overview,
            first_air_date = input.first_air_date,
            vote_average = input.vote_average,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToMovieEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            release_date = input.release_date,
            vote_average = input.vote_average,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
        )

    fun mapDomainToTvShowEntity(input: TvShow) =
        TvShowEntity(
            id = input.id,
            name = input.name,
            overview = input.overview,
            first_air_date = input.first_air_date,
            vote_average = input.vote_average,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
        )
}
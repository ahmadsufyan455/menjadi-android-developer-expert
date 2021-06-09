package com.fyndev.moviecatalogue.core.data.source.local

import com.fyndev.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getTvShows(): Flow<List<TvShowEntity>> = movieDao.getTvShows()

    fun getMovieById(id: Int): Flow<MovieEntity> = movieDao.getMovieById(id)

    fun getTvShowById(id: Int): Flow<TvShowEntity> = movieDao.getTvShowById(id)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    fun updateTvShow(tvShow: TvShowEntity) {
        movieDao.updateTvShow(tvShow)
    }

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun setMovieStatus(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovie(movie)
    }

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = movieDao.getFavoriteTvShow()

    fun setTvShowStatus(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        movieDao.updateTvShow(tvShow)
    }
}
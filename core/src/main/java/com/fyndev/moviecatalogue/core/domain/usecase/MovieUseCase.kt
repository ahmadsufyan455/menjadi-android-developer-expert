package com.fyndev.moviecatalogue.core.domain.usecase

import com.fyndev.moviecatalogue.core.data.Resource
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getTvShow(): Flow<Resource<List<TvShow>>>

    fun getDetailMovie(id: Int): Flow<Resource<Movie>>

    fun getDetailTvShow(id: Int): Flow<Resource<TvShow>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, isFavorite: Boolean)

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean)
}
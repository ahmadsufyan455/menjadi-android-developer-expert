package com.fyndev.moviecatalogue.core.domain.usecase

import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getMovies() = movieRepository.getMovies()

    override fun getTvShow() = movieRepository.getTvShow()

    override fun getDetailMovie(id: Int) = movieRepository.getDetailMovie(id)

    override fun getDetailTvShow(id: Int) = movieRepository.getDetailTvShow(id)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        movieRepository.setFavoriteMovie(movie, isFavorite)
    }

    override fun getFavoriteTvShow() = movieRepository.getFavoriteTvShow()

    override fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) {
        movieRepository.setFavoriteTvShow(tvShow, isFavorite)
    }
}
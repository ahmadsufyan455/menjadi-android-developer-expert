package com.fyndev.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fyndev.moviecatalogue.core.data.Resource
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var detailMovie: LiveData<Resource<Movie>>
    private lateinit var detailTvShow: LiveData<Resource<TvShow>>

    fun setState(id: Int, data: String) {
        when (data) {
            MOVIE -> detailMovie = movieUseCase.getDetailMovie(id).asLiveData()
            TV_SHOW -> detailTvShow = movieUseCase.getDetailTvShow(id).asLiveData()
        }
    }

    fun getDetailMovie() = detailMovie

    fun getDetailTvShow() = detailTvShow

    fun setFavoriteMovie() {
        val movieSource = detailMovie.value
        if (movieSource?.data != null) {
            val newState = !movieSource.data!!.isFavorite
            movieUseCase.setFavoriteMovie(movieSource.data!!, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShowSource = detailTvShow.value
        if (tvShowSource?.data != null) {
            val newState = !tvShowSource.data!!.isFavorite
            movieUseCase.setFavoriteTvShow(tvShowSource.data!!, newState)
        }
    }
}
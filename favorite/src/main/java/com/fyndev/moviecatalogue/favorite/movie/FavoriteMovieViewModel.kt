package com.fyndev.moviecatalogue.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie() = movieUseCase.getFavoriteMovie().asLiveData()
}
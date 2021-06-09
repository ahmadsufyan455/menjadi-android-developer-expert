package com.fyndev.moviecatalogue.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase

class FavoriteTvShowViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteTvShow() = movieUseCase.getFavoriteTvShow().asLiveData()
}
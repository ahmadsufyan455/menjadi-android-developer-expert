package com.fyndev.moviecatalogue.home.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase

class TvShowViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getTvShow() = movieUseCase.getTvShow().asLiveData()

}
package com.fyndev.moviecatalogue.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDataMovie() = movieUseCase.getMovies().asLiveData()

}
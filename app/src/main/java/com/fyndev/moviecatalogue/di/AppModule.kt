package com.fyndev.moviecatalogue.di

import com.fyndev.moviecatalogue.core.domain.usecase.MovieInteractor
import com.fyndev.moviecatalogue.core.domain.usecase.MovieUseCase
import com.fyndev.moviecatalogue.detail.DetailViewModel
import com.fyndev.moviecatalogue.home.movie.MovieViewModel
import com.fyndev.moviecatalogue.home.tv.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
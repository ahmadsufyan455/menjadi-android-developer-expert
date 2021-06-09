package com.fyndev.moviecatalogue.favorite

import com.fyndev.moviecatalogue.favorite.movie.FavoriteMovieViewModel
import com.fyndev.moviecatalogue.favorite.tvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}
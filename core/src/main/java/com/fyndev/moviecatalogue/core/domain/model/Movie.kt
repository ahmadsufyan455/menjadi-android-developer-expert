package com.fyndev.moviecatalogue.core.domain.model

data class Movie(
    val id: Int = 0,
    val title: String? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val vote_average: String? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    var isFavorite: Boolean = false
)

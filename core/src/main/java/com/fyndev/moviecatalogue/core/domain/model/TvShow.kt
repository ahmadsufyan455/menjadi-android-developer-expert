package com.fyndev.moviecatalogue.core.domain.model

data class TvShow(
    val id: Int = 0,
    val name: String? = null,
    val overview: String? = null,
    val first_air_date: String? = null,
    val vote_average: Double = 0.0,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    var isFavorite: Boolean = false
)

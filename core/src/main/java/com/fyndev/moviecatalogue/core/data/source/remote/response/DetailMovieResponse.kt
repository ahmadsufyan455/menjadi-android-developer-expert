package com.fyndev.moviecatalogue.core.data.source.remote.response

data class DetailMovieResponse(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val release_date: String = "",
    val vote_average: String = "",
    val poster_path: String = "",
    val backdrop_path: String = ""
)
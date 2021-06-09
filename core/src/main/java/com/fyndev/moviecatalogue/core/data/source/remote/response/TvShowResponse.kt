package com.fyndev.moviecatalogue.core.data.source.remote.response

data class TvShowResponse(
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val first_air_date: String = "",
    val vote_average: String = "",
    val poster_path: String = "",
    val backdrop_path: String = ""
)

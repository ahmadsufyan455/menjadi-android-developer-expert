package com.fyndev.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("release_date")
    val release_date: String = "",

    @field:SerializedName("vote_average")
    val vote_average: String = "",

    @field:SerializedName("poster_path")
    val poster_path: String = "",

    @field:SerializedName("backdrop_path")
    val backdrop_path: String = ""
)
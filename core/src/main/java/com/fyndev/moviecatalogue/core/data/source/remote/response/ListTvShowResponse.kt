package com.fyndev.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    @field:SerializedName("results")
    val results: ArrayList<TvShowResponse>
)

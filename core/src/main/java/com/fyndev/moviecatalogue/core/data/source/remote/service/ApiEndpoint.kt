package com.fyndev.moviecatalogue.core.data.source.remote.service

import com.fyndev.moviecatalogue.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    /*
     * Movies
     */
    @GET("movie/now_playing")
    suspend fun getMovie(@Query("api_key") apiKey: String): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): DetailMovieResponse

    /*
    * Tv Show
    */
    @GET("tv/on_the_air")
    suspend fun getTvShow(@Query("api_key") apiKey: String): ListTvShowResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): DetailTvShowResponse
}
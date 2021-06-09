package com.fyndev.moviecatalogue.core.data.source.remote

import android.util.Log
import com.fyndev.moviecatalogue.core.BuildConfig.MOVIE_KEY
import com.fyndev.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.core.data.source.remote.service.ApiEndpoint
import com.fyndev.moviecatalogue.core.data.source.remote.service.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiEndpoint: ApiEndpoint) {
    /*
     * List
     */
    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        // get data from api
        return flow {
            try {
                val response = apiEndpoint.getMovie(MOVIE_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    // emit something
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiEndpoint.getTvShow(MOVIE_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    /*
     * Detail
     */
    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = apiEndpoint.getDetailMovie(id, MOVIE_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<DetailTvShowResponse>> {
        return flow {
            try {
                val response = apiEndpoint.getDetailTvShow(id, MOVIE_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
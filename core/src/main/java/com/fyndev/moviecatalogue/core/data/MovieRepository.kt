package com.fyndev.moviecatalogue.core.data

import com.fyndev.moviecatalogue.core.data.source.local.LocalDataSource
import com.fyndev.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.core.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.core.data.source.remote.service.ApiResponse
import com.fyndev.moviecatalogue.core.domain.model.Movie
import com.fyndev.moviecatalogue.core.domain.model.TvShow
import com.fyndev.moviecatalogue.core.domain.repository.IMovieRepository
import com.fyndev.moviecatalogue.core.utils.AppExecutors
import com.fyndev.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return object :
            com.fyndev.moviecatalogue.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapEntitiesToMovieDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.mapResponseToMovieEntities(data)
                localDataSource.insertMovies(movies)
            }
        }.asFLow()
    }

    override fun getTvShow(): Flow<Resource<List<TvShow>>> {
        return object :
            com.fyndev.moviecatalogue.core.data.NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShows().map { DataMapper.mapEntitiesToTvShowDomain(it) }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShow()
            }

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShows = DataMapper.mapResponseToTvShowEntities(data)
                localDataSource.insertTvShows(tvShows)
            }

        }.asFLow()
    }

    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> {
        return object :
            com.fyndev.moviecatalogue.core.data.NetworkBoundResource<Movie, DetailMovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id)
                    .map { DataMapper.mapEntitiesToDetailMovieDomain(it) }
            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.release_date,
                    data.vote_average,
                    data.poster_path,
                    data.backdrop_path
                )
                localDataSource.updateMovie(movie)
            }
        }.asFLow()
    }

    override fun getDetailTvShow(id: Int): Flow<Resource<TvShow>> {
        return object :
            com.fyndev.moviecatalogue.core.data.NetworkBoundResource<TvShow, DetailTvShowResponse>() {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getTvShowById(id)
                    .map { DataMapper.mapEntitiesToDetailTvSHowDomain(it) }
            }

            override fun shouldFetch(data: TvShow?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override suspend fun saveCallResult(data: DetailTvShowResponse) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.first_air_date,
                    data.vote_average,
                    data.poster_path,
                    data.backdrop_path
                )
                localDataSource.updateTvShow(tvShow)
            }
        }.asFLow()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToMovieDomain(it) }
    }

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShow().map { DataMapper.mapEntitiesToTvShowDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        val movieEntity = DataMapper.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieStatus(movieEntity, isFavorite) }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) {
        val tvShowEntity = DataMapper.mapDomainToTvShowEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowStatus(tvShowEntity, isFavorite) }
    }
}
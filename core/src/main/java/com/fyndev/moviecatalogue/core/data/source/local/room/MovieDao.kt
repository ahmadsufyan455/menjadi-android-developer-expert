package com.fyndev.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.fyndev.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_entities")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE id = :id")
    fun getTvShowById(id: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_entities WHERE isFavorite = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowEntity>>
}
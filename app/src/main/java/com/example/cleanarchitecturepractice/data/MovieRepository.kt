package com.example.cleanarchitecturepractice.data

import com.example.cleanarchitecturepractice.MovieList
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource: MovieDataSource) {
    suspend fun getPopularMovies(): Result<MovieList> {
        return movieDataSource.getPopularMovies()
    }
}
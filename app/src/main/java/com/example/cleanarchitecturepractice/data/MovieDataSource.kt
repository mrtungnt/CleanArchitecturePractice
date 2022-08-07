package com.example.cleanarchitecturepractice.data

import com.example.cleanarchitecturepractice.MovieList
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val movieApi: MovieAPI) {
    suspend fun getPopularMovies(): Result<MovieList> = movieApi.getPopularMovies()
}
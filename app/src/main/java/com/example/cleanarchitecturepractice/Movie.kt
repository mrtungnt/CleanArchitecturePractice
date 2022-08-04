package com.example.cleanarchitecturepractice

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val poster_path: String, val original_title: String)

@Serializable
data class MovieList(val page: Int, val results: List<Movie>)
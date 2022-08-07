package com.example.cleanarchitecturepractice.data

import com.example.cleanarchitecturepractice.BuildConfig
import com.example.cleanarchitecturepractice.MovieList
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface MovieAPI {
    suspend fun getPopularMovies(): Result<MovieList>
}

object MovieAPIImpl : MovieAPI {
    private val client = HttpClient(CIO)

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getPopularMovies(): Result<MovieList> {
        val response: HttpResponse =
            client.get("${BuildConfig.tmdb_base_url}/movie/popular?page=510") {
                this.parameter("api_key", BuildConfig.api_key)
            }
        val result: MovieList?
        try {
            result =
                json.decodeFromString<MovieList>(response.bodyAsText())
        } catch (e: Exception) {
            return Result.failure(PageException(response.bodyAsText()))
        }

        return Result.success(result)
    }
}

class PageException(private val msg: String) : Exception() {
    override val message: String
        get() = msg
}
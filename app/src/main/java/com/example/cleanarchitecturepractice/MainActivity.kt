package com.example.cleanarchitecturepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.cleanarchitecturepractice.ui.theme.CleanArchitecturePracticeTheme
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitecturePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var movieList: MovieList? by remember {
                        mutableStateOf(null)
                    }
                    val client = HttpClient(CIO)
                    LaunchedEffect(key1 = Unit) {
                        val response: HttpResponse =
                            client.get("https://api.themoviedb.org/3/movie/popular") {
                                this.parameter("api_key", BuildConfig.api_key)
                            }
                        val json = response.bodyAsText()
                        movieList =
                            Json { ignoreUnknownKeys = true }.decodeFromString<MovieList>(json)
                    }
                    Column() {
                        val nMoviesInPage = movieList?.results?.size ?: 0
                        val movie =
                            if (nMoviesInPage > 0) movieList?.results?.get(nMoviesInPage - 1) else null
                        Image(
                            painter = rememberAsyncImagePainter("${BuildConfig.tmdb_img_base_url}${movie?.poster_path}"),
                            null
                        )
                        Text(text = movie?.original_title ?: "")
                    }
                }
            }
        }
    }
}
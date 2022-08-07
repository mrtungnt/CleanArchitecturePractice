package com.example.cleanarchitecturepractice

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cleanarchitecturepractice.ui.theme.CleanArchitecturePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var isConnected by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val connMgr =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connMgr.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            NetworkCallbackExt(this)
        )
        setContent {
            CleanArchitecturePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*var movieList: MovieList? by remember {
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
                    }*/
                    if (isConnected)
                        PopularMoviesScreen(viewModel())
                    else Toast(LocalContext.current).apply { setText("No connection");show() }
                }
            }
        }
    }
}

class NetworkCallbackExt(private val activity: MainActivity) :
    ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        activity.isConnected = true
    }
}



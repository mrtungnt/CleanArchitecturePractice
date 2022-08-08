package com.example.cleanarchitecturepractice

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun PopularMoviesScreen(viewModel: PopularMoviesViewModel) {
    val state = viewModel.uiState.collectAsState()
    Text(
        text = state.value.movieList?.results?.get(0)?.original_title
            ?: state.value.exceptionDescription ?: ""
    )
}
package com.example.cleanarchitecturepractice

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun PopularMoviesScreen(viewModel: PopularMoviesViewModel) {
    Text(text = viewModel.uiStateFlow.collectAsState().value)
}
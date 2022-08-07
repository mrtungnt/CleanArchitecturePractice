package com.example.cleanarchitecturepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturepractice.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    val uiStateFlow = MutableStateFlow("Starting...")

    init {
        viewModelScope.launch {
            val r = movieRepository.getPopularMovies()
            if (r.isFailure) {
                uiStateFlow.update {
                    println("old value: $it");r.exceptionOrNull()?.message
                    ?: "Unspecified exception"
                }
            } else {
                uiStateFlow.update { r.getOrNull()?.results?.get(0)?.original_title ?: "" }
            }
        }
    }
}
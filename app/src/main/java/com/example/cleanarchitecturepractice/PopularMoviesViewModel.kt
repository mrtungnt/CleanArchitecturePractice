package com.example.cleanarchitecturepractice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturepractice.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var uiState = MutableStateFlow(UIState())

    init {
        viewModelScope.launch {
            val r = movieRepository.getPopularMovies()
            if (r.isSuccess) {
                uiState.update { it.copy(movieList = r.getOrNull()) }
            } else {
                uiState.update {
                    it.copy(
                        exceptionDescription = r.exceptionOrNull()?.message
                            ?: "Unspecified exception"
                    )
                }
            }
        }
    }
}

data class UIState(val movieList: MovieList? = null, val exceptionDescription: String? = null)
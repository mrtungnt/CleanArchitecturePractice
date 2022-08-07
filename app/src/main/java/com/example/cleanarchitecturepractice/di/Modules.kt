package com.example.cleanarchitecturepractice.di

import com.example.cleanarchitecturepractice.data.MovieAPI
import com.example.cleanarchitecturepractice.data.MovieAPIImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieAPIModule {
    @Singleton
    @Provides
    fun provideMovieAPI(): MovieAPI = MovieAPIImpl
}
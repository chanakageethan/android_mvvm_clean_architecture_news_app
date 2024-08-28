package com.example.mvvm_clean_architecure_2.presentation.di

import android.app.Application
import com.example.mvvm_clean_architecure_2.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter():NewsAdapter{
        return NewsAdapter()
    }
}
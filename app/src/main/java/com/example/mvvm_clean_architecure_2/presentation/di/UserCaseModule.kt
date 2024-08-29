package com.example.mvvm_clean_architecure_2.presentation.di

import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSavedNewsUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSearchedUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserCaseModule {

    @Singleton
    @Provides
    fun provideNewsHeadlinesUseCase(
        newsRepository: NewsRepository
    ):GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsHeadlinesUseCase(
        newsRepository: NewsRepository
    ):GetSearchedUseCase{
        return GetSearchedUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(
        newsRepository: NewsRepository
    ):GetSavedNewsUseCase{
        return GetSavedNewsUseCase(newsRepository)
    }
}
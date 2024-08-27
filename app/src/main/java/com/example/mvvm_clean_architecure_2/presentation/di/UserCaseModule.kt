package com.example.mvvm_clean_architecure_2.presentation.di

import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
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
}
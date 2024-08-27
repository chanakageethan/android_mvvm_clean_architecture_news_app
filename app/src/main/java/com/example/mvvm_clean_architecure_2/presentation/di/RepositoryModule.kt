package com.example.mvvm_clean_architecure_2.presentation.di

import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository
import com.example.mvvm_clean_architecure_2.repository.NewsRepositoryImpl
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}
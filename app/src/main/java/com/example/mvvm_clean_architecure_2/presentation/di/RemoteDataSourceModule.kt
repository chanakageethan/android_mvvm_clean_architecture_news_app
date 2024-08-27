package com.example.mvvm_clean_architecure_2.presentation.di

import com.example.mvvm_clean_architecure_2.data.api.NewsAPIService
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsRemoteDataSource
import com.example.mvvm_clean_architecure_2.repository.dataSourceIml.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {


    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ):NewsRemoteDataSource{
        return  NewsRemoteDataSourceImpl(newsAPIService)
    }
}
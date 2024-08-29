package com.example.mvvm_clean_architecure_2.presentation.di

import com.example.mvvm_clean_architecure_2.data.db.ArticleDAO
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsLocalDataSource
import com.example.mvvm_clean_architecure_2.repository.dataSourceIml.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {


    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}
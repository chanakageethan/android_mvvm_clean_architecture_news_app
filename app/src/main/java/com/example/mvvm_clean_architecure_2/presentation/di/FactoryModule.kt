package com.example.mvvm_clean_architecure_2.presentation.di

import android.app.Application
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {


    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    ):NewsViewModelFactory{
        return NewsViewModelFactory(
            application,
            getNewsHeadlinesUseCase
        )
    }
}
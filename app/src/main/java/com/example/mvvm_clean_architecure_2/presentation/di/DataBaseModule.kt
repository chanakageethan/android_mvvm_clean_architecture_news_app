package com.example.mvvm_clean_architecure_2.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_architecure_2.data.db.ArticleDAO
import com.example.mvvm_clean_architecure_2.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providerNewsDatabase(app:Application):ArticleDatabase{
        return Room.databaseBuilder(app,ArticleDatabase::class.java,"news_db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providerNewsDao(articleDatabase: ArticleDatabase):ArticleDAO{
        return articleDatabase.getArticleDAO()
    }
}
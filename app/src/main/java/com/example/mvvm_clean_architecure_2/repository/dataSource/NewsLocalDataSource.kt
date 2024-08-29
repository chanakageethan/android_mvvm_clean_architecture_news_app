package com.example.mvvm_clean_architecure_2.repository.dataSource

import com.example.mvvm_clean_achitecture.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteArticlesFromDB(article: Article)
}
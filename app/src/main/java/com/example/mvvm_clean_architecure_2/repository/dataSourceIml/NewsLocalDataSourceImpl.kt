package com.example.mvvm_clean_architecure_2.repository.dataSourceIml

import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_architecure_2.data.db.ArticleDAO
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
): NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override  fun getSavedArticles(): Flow<List<Article>> {
       return  articleDAO.getAllArticles()
    }
}
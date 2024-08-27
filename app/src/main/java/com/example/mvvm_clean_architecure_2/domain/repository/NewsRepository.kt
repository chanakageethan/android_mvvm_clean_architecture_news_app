package com.example.mvvm_clean_achitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_achitecture.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country:String,page:Int): Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery:String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
     fun getSavedNews(): Flow<List<Article>>
}
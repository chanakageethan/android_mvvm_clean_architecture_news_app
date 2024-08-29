package com.example.mvvm_clean_architecure_2.repository

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsLocalDataSource
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews(
                country,
                searchQuery,
                page
            )
        )
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }
}
package com.example.mvvm_clean_architecure_2.repository.dataSourceIml

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_architecure_2.data.api.NewsAPIService
import com.example.mvvm_clean_architecure_2.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,

):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)

    }
}
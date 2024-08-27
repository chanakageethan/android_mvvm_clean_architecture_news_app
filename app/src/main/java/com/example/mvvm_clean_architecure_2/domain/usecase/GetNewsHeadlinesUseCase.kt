package com.example.mvvm_clean_achitecture.domain.usecase

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,page:Int):Resource<APIResponse>{
        return newsRepository.getNewsHeadlines(country,page)
    }
}
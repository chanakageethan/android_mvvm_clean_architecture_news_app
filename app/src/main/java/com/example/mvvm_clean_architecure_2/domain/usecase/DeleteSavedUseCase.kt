package com.example.mvvm_clean_achitecture.domain.usecase

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository

class DeleteSavedUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}
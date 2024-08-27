package com.example.mvvm_clean_achitecture.domain.usecase

import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
     fun execute() : Flow<List<Article>>{
         return newsRepository.getSavedNews()
     }
}
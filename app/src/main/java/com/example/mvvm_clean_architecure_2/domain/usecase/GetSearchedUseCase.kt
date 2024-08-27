package com.example.mvvm_clean_achitecture.domain.usecase

import android.app.DownloadManager.Query
import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository

class GetSearchedUseCase(private val newsRepository: NewsRepository) {
  suspend fun  execute(searchQuery: String): Resource<APIResponse> {
      return newsRepository.getSearchedNews(searchQuery)
  }

}
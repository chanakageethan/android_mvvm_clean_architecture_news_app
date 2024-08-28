package com.example.mvvm_clean_achitecture.domain.usecase

import android.app.DownloadManager.Query
import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.repository.NewsRepository

class GetSearchedUseCase(private val newsRepository: NewsRepository) {
  suspend fun  execute(country:String,searchQuery: String,page:Int): Resource<APIResponse> {
      return newsRepository.getSearchedNews(country,searchQuery,page)
  }

}
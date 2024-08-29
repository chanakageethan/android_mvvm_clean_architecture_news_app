package com.example.mvvm_clean_architecure_2.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_achitecture.data.util.Resource
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSavedNewsUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSearchedUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedUseCase: GetSearchedUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase

    ) : AndroidViewModel(app) {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()


    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadlines.postValue(Resource.Loading())

        try {
            if (isInternetAvailable(app)) {
                newsHeadlines.postValue(Resource.Loading())
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error(message = "Internet is not available"))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }

    }


    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }


    //search
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(
        country: String,
        searchQuery: String,
        page: Int
    ) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)) {
                val response = getSearchedUseCase.execute(
                    country,
                    searchQuery,
                    page
                )
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }


    //localData

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews()= liveData{
        getSavedNewsUseCase.execute().collect(){
            emit(it)
        }
    }


}
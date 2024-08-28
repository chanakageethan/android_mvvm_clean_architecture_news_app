package com.example.mvvm_clean_architecure_2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSearchedUseCase

class NewsViewModelFactory(
    private  val app:Application,
    private  val getNewsHeadlinesUseCase:GetNewsHeadlinesUseCase,
    private val getSearchedUseCase: GetSearchedUseCase

):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedUseCase
        )as T
    }
 }
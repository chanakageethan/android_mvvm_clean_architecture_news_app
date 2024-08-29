package com.example.mvvm_clean_architecure_2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_clean_achitecture.domain.usecase.DeleteSavedUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSavedNewsUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.GetSearchedUseCase
import com.example.mvvm_clean_achitecture.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedUseCase: GetSearchedUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedUseCase: DeleteSavedUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedUseCase
        ) as T
    }
}
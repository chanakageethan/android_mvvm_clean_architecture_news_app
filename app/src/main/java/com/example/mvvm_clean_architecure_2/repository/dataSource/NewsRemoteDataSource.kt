package com.example.mvvm_clean_architecure_2.repository.dataSource

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String,page:Int):Response<APIResponse>
}
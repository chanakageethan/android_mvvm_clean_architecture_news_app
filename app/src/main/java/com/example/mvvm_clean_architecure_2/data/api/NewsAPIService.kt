package com.example.mvvm_clean_architecure_2.data.api

import com.example.mvvm_clean_achitecture.data.model.APIResponse
import com.example.mvvm_clean_architecure_2.BuildConfig
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String= BuildConfig.API_KEY,
    ): Response<APIResponse>
}
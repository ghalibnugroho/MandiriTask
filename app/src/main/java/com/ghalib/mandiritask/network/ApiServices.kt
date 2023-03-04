package com.ghalib.mandiritask.network

import com.ghalib.mandiritask.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String
    ): Response

    @GET("everything")
    suspend fun everything(
        @Query("q") q:String,
        @Query("from") from:String,
        @Query("to") to:String,
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey:String
    ): Response
}
package com.ghalib.mandiritask.di

import com.ghalib.mandiritask.network.ApiServices
import com.ghalib.mandiritask.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object Constant {
    const val BASE_URL ="https://newsapi.org/v2/"
    const val APIKEY = "a3b027ff196243c3b35ce9fbede8c5ca"
}

@InstallIn(SingletonComponent::class)
@Module
object RetrofitApi {

    @Singleton
    @Provides
    fun provideApiService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(ApiServices::class.java)
    }

    fun getOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .build()

    @Provides
    fun providesNewsRepository(api: ApiServices): NewsRepository{
        return NewsRepository(apiServices = api)
    }
}
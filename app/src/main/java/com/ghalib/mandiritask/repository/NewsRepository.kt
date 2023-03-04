package com.ghalib.mandiritask.repository

import com.ghalib.mandiritask.di.Constant
import com.ghalib.mandiritask.models.ArticlesItem
import com.ghalib.mandiritask.models.Response
import com.ghalib.mandiritask.network.ApiServices
import com.ghalib.mandiritask.views.utils.Resource
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiServices: ApiServices
){
    suspend fun getTopHeadlines(): Resource<List<ArticlesItem>>{
        return try{
            var result = apiServices.topHeadlines("bbc-news", Constant.APIKEY)
            var formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
            if(result.status.equals("ok")){
                result.articles.map {
                    var date = ZonedDateTime.parse(it.publishedAt)
                    var formatting = formatter.format(date)
                    it.publishedAt = formatting.toString()
                }
                Resource.Success(result.articles)
            }else{
                Resource.Failure(exception = Exception("Data couldn't be loaded from server"))
            }
        }catch (exception: Exception){
            Resource.Failure(exception = exception)
        }
    }

    suspend fun getAllNews(): Resource<List<ArticlesItem>>{
        return try{
            var result = apiServices.everything("apple","2023-02-28", "2023-02-28", "popularity", Constant.APIKEY)
            var formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
            if(result.status.equals("ok")){
                result.articles.map {
                    var date = ZonedDateTime.parse(it.publishedAt)
                    var formatting = formatter.format(date)
                    it.publishedAt = formatting.toString()
                }
                Resource.Success(result.articles)
            }else{
                Resource.Failure(exception = Exception("Data couldn't be loaded from server"))
            }
        }catch (exception: Exception){
            Resource.Failure(exception = exception)
        }
    }
}
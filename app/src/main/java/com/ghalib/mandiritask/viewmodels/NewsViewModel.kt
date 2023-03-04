package com.ghalib.mandiritask.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghalib.mandiritask.models.ArticlesItem
import com.ghalib.mandiritask.repository.NewsRepository
import com.ghalib.mandiritask.views.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    var sizeTopHeadlines by mutableStateOf(0)
    var topHeadlinesState by mutableStateOf(emptyList<ArticlesItem>())

    var sizeAllNews by mutableStateOf(0)
    var allNewsState by mutableStateOf(emptyList<ArticlesItem>())

    fun getTopHeadlines() = viewModelScope.launch {
        val result = newsRepository.getTopHeadlines()
        result.let {
            when(it){
                is Resource.Failure -> {sizeTopHeadlines = 0}
                Resource.Loading -> {sizeTopHeadlines = 0}
                is Resource.Success -> {
                    sizeTopHeadlines = it.result.size
                    topHeadlinesState = it.result
                }
            }
        }
    }

    fun getAllNews() = viewModelScope.launch {
        delay(1000)
        val result = newsRepository.getAllNews()
        result.let {
            when(it){
                is Resource.Failure -> {sizeAllNews = 0}
                Resource.Loading -> {sizeAllNews = 0}
                is Resource.Success -> {
                    sizeAllNews = it.result.size
                    allNewsState = it.result
                }
            }
        }
    }
}


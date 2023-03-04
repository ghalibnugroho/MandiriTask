package com.ghalib.mandiritask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghalib.mandiritask.ui.theme.MandiritaskTheme
import com.ghalib.mandiritask.viewmodels.NewsViewModel
import com.ghalib.mandiritask.views.home.allNewsItem
import com.ghalib.mandiritask.views.home.topHeadlinesNewsItem
import com.ghalib.mandiritask.views.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MandiritaskTheme {
                val viewModel: NewsViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit){
                    viewModel.getTopHeadlines()
                    viewModel.getAllNews()
                }
                val sizeTopHeadline = viewModel.sizeTopHeadlines
                val topHeadlineState = viewModel.topHeadlinesState
                val sizeAllNews = viewModel.sizeAllNews
                val allNewsState = viewModel.allNewsState
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    Column{
                        Row(
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    top = 20.dp,
                                    bottom = 10.dp
                                )
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.bbc_news_result),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .width(40.dp)
                                    .height(40.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                            Text(
                                text = "BBC NEWS",
                                style = TextStyle(
                                    fontWeight = FontWeight(1000),
                                    fontSize = 30.sp
                                ),
                                modifier = Modifier.padding(
                                    start = 7.dp,
                                    top = 0.dp,
                                    end = 7.dp,
                                    bottom = 0.dp
                                )
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                        }
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ){
                            item{
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(
                                    text = "Top Headlines",
                                    style = TextStyle(
                                        fontWeight = FontWeight(800),
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 0.dp,
                                        end = 7.dp,
                                        bottom = 0.dp
                                    )
                                )
                            }
                            item{
                                if(sizeTopHeadline>0){
                                    LazyRow{
                                        items(sizeTopHeadline){
                                            topHeadlineState.map {
                                                topHeadlinesNewsItem(data = it)
                                            }
                                        }
                                    }
                                }else{
                                    showProgressBar()
                                }
                            }
                            item{
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(
                                    text = "Tech News",
                                    style = TextStyle(
                                        fontWeight = FontWeight(800),
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 0.dp,
                                        end = 7.dp,
                                        bottom = 0.dp
                                    )
                                )
                            }
                            if(sizeAllNews>0){
                                items(sizeAllNews){
                                    allNewsItem(data = allNewsState[it])
                                }
                            }else{
                                item{
                                    showProgressBar()
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
package com.ghalib.mandiritask.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ghalib.mandiritask.R
import com.ghalib.mandiritask.models.ArticlesItem
import com.ghalib.mandiritask.models.Source

@Preview
@Composable
fun topHeadlinesNewsItem(
    @PreviewParameter(SampleTopHeadlinesNews::class) data: ArticlesItem
){
    Card(
        modifier = Modifier
            .width(380.dp)
            .padding(
                5.dp
            )
            .clickable { }
        ,elevation = 10.dp,
    ){
        Column{
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data(data.urlToImage)
                        .build(),
                    filterQuality = FilterQuality.High,
                    error = painterResource(id = R.drawable.image_placeholder),
                    placeholder = painterResource(id = R.drawable.image_placeholder)
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clip(shape = RoundedCornerShape(3.dp)),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    maxLines = 1,
                    text = data.title,
                    style = TextStyle(
                        fontWeight = FontWeight(750),
                        fontSize = 18.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 7.dp,
                        top = 0.dp,
                        end = 7.dp,
                        bottom = 0.dp
                    )
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = if(data.author == null) "Anonymous" else data.author,
                    style = TextStyle(
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = if(data.publishedAt == null) "Unkown Date" else data.publishedAt,
                    style = TextStyle(
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

class SampleTopHeadlinesNews : PreviewParameterProvider<ArticlesItem>{
    override val values = sequenceOf(
        ArticlesItem(
            publishedAt = "2023-02-28T21:52:22.7668142Z",
            author = "BBC News",
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/A7F7/production/_128799924_gettyimages-1450783696.jpg",
            description = "Beijing accuses Washington of attempting to use state power to suppress foreign companies.",
            source = Source("BBC News", "bbc-news"),
            title = "China hits out at US over TikTok ban on federal devices",
            url = "http://www.bbc.co.uk/news/world-asia-china-64795548",
            content = "China has accused the US of overreacting after federal employees were ordered to remove the video app TikTok from government-issued phones.\r\nOn Monday, the White House gave government agencies 30 day… [+3392 chars]"
        )
    )
}


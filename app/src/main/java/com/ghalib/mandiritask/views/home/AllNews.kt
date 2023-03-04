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
fun allNewsItem(
    @PreviewParameter(SampleAllNews::class) data: ArticlesItem
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                5.dp
            )
            .clickable { }
        ,elevation = 10.dp,
    ){
        Row{
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data(data.urlToImage)
                        .build(),
                    filterQuality = FilterQuality.High,
                    error = painterResource(id = R.drawable.image_placeholder_news),
                    placeholder = painterResource(id = R.drawable.image_placeholder)
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit
            )
            Column{
                Text(
                    maxLines = 2,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        maxLines = 1,
                        text = if(data.author == null) "Anonymous" else data.author,
                        style = TextStyle(
                            fontWeight = FontWeight(300),
                            fontSize = 14.sp
                        ),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(160.dp)
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
}

class SampleAllNews : PreviewParameterProvider<ArticlesItem> {
    override val values = sequenceOf(
        ArticlesItem(
            publishedAt = "2023-02-28T22:00:00Z",
            author = null,
            urlToImage = "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/d89b0bc74cc33dda3d9dbd480864eaaa.jpg",
            description = "Sometimes a deal can feel too good to pass up, and that can especially be the case with a gift card deal—particularly since federal law gives you five years from the date a gift card is activated to use it before it expires. If you’ve got a purchase planned i…",
            source = Source("Lifehacker.com", "null"),
            title = "Get These Gift Card Deals While You Can",
            url = "https://lifehacker.com/get-these-gift-card-deals-while-you-can-1850168858",
            content = "Sometimes a deal can feel too good to pass up, and that can especially be the case with a gift card dealparticularly since federal law gives you five years from the date a gift card is activated to u… [+2211 chars]"
        )
    )
}
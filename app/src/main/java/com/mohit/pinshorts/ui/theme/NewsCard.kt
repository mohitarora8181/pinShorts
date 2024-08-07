package com.mohit.pinshorts.ui.theme
import com.mohit.pinshorts.entities.NewsResult
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.mohit.pinshorts.MainApplication
import com.mohit.pinshorts.db.NewsDataDao
import com.mohit.pinshorts.entities.NewsResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NewsCard(data: NewsResult,navController: NavController,showToast:()->Unit,getDatabaseInstant:NewsDataDao){


    val addCoroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(20.dp),
            )
            .padding(15.dp)
    ) {
        AsyncImage(
            model = data.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(15.dp))
                .clickable(onClick = {
                    navController.navigate("newsDetails?url=${data.link}")
                })
            ,
            contentScale = ContentScale.Crop,
            error = rememberAsyncImagePainter(model = "https://jkrobotics.in/images/logo.png")
        )
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(text = data.title , modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.9f)
                .clickable(onClick = {
                    navController.navigate("newsDetails?url=${data.link}")
                }),
                fontWeight = FontWeight.SemiBold
            )
            Icon(Icons.Outlined.BookmarkAdd,"bookmark",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        onClick = {
                            addCoroutine.launch(Dispatchers.IO) {
                                getDatabaseInstant.addBookmark(
                                    NewsResultEntity(
                                        title = data.title,
                                        link = data.link,
                                        thumbnail = data.thumbnail,
                                        source = data.source,
                                        date = data.date
                                    )
                                )
                            }
                            showToast()
                        }
                    )
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
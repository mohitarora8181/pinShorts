package com.mohit.pinshorts.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.mohit.pinshorts.MainApplication
import com.mohit.pinshorts.Screens
import com.mohit.pinshorts.db.NewsDataDao
import com.mohit.pinshorts.entities.NewsResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun BookMarks(navController: NavController) {
    val newsDao =  MainApplication.newsDatabase.getNewsDao()

    suspend fun getData():List<NewsResultEntity> = withContext(Dispatchers.IO){
        newsDao.getBookmarks()
    }
    val deleteScope = rememberCoroutineScope()

    val data = remember {
        mutableStateOf<List<NewsResultEntity>>(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        data.value = getData()
    }

    fun deleteBookMark():(Int)->Unit = {id->
        deleteScope.launch(Dispatchers.IO) {
            newsDao.deleteBookMark(id)
        }
        data.value = data.value.filterNot { it.id == id }
    }

    Column (modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)){

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(text = "My Bookmarks", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }

        LazyColumn (modifier=Modifier.padding(horizontal = 15.dp)) {
            items(data.value){
                BookMarkCard(data = it,navController,deleteBookMark = deleteBookMark())
            }
        }
    }
}


@Composable
fun BookMarkCard(data:NewsResultEntity,navController: NavController,deleteBookMark:(Int)->Unit){
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
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = data.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        navController.navigate("newsDetails?url=${data.link}")
                    })
                ,
                contentScale = ContentScale.Crop,
                error = rememberAsyncImagePainter(model = "https://jkrobotics.in/images/logo.png")
            )
            Text(text = data.title , modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth()
                .clickable(onClick = {
                    navController.navigate("newsDetails?url=${data.link}")
                }),
                fontWeight = FontWeight.SemiBold
            )
        }
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
            Icon(
                Icons.Outlined.Delete,"delete",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        onClick = { deleteBookMark(data.id) }
                    )
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}
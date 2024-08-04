package com.mohit.pinshorts.pages

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mohit.pinshorts.entities.NewsData
import com.mohit.pinshorts.ui.theme.NewsCard
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json

@Composable
fun Home(navController: NavController){

    val context = LocalContext.current

    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getData(): NewsData?{
        val baseUrl = "https://serpapi.com/search?engine=google_news&gl=in"
        val search = "latest_news"
        val apiKey = "22d65f1551ddafb70243069f7eeb76b0ddf4bbc537f321f5d839318197694015"
        try{
            val resp: HttpResponse = client.get("$baseUrl&q=$search&api_key=$apiKey")
//            Log.d("TAG", (Json.parseToJsonElement(resp.body()).jsonObject).toString())
            return resp.body()
        }catch (e:Exception){
            println("Error :- ${e.message}")
            return null
        }
    }

    val data = produceState<NewsData?>(initialValue = null ) {
        value = getData()
    }


    fun showToast (){
        println("venvkjdsbksdbckjsncksnckjnaskj")
        Toast.makeText(context,"Added to Bookmarks", Toast.LENGTH_SHORT).show()
    }

    Column {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(text = "Latest News", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = "My Bookmarks",
                fontSize = 15.sp,
                color = Color.Blue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate("bookmarks")
                    }
                )
            )
        }

        if(data.value?.news_results != null){
            LazyColumn (modifier=Modifier.padding(horizontal = 15.dp)) {
                items(data.value!!.news_results){
                    NewsCard(data = it,navController, showToast = { showToast() })
                }
            }
        }
    }
}
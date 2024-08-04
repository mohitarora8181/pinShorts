package com.mohit.pinshorts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mohit.pinshorts.pages.BookMarks
import com.mohit.pinshorts.pages.Home
import com.mohit.pinshorts.pages.NewsDetails
import com.mohit.pinshorts.ui.theme.PinShortsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PinShortsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(navController =  navController, startDestination = "home" ){
                        composable(route = Screens.HomeScreen.route){
                            Home(navController)
                        }
                        composable(route = Screens.BookmarksScreen.route){
                            BookMarks(navController)
                        }
                        composable(route = Screens.NewsDetailsScreen.route,
                            arguments = listOf(
                                navArgument("url"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            NewsDetails(url = it.arguments?.getString("url"),modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}
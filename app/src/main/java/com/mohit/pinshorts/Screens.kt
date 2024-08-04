package com.mohit.pinshorts

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(val route:String) {
    object HomeScreen:Screens("home")
    object BookmarksScreen:Screens("bookmarks")
    object NewsDetailsScreen : Screens("newsDetails?url={url}")
}
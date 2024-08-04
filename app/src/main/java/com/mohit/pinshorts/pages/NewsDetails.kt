package com.mohit.pinshorts.pages

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsDetails(url:String?,modifier: Modifier) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.webViewClient = object:WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if(url != null && url.startsWith("https://google.com")){
                        return true
                    }
                    return false
                }
            }
        }
    }, update = {
        it.loadUrl(url?:"https://www.google.com/")

    }, modifier = modifier.fillMaxSize()
    )
}
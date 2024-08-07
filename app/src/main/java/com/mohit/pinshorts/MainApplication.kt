package com.mohit.pinshorts

import android.app.Application
import androidx.room.Room
import com.mohit.pinshorts.db.NewsDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication :Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
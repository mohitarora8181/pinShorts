package com.mohit.pinshorts

import android.app.Application
import androidx.room.Room
import com.mohit.pinshorts.db.NewsDatabase

class MainApplication :Application() {
    companion object{
        lateinit var newsDatabase:NewsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        newsDatabase = Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java,
            NewsDatabase.NAME
        ).build()
    }
}
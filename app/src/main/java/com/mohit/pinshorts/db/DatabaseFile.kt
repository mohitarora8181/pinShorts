package com.mohit.pinshorts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohit.pinshorts.entities.NewsResultEntity
import com.mohit.pinshorts.entities.SourceTypeConvertor


@Database([NewsResultEntity::class],version=1)
@TypeConverters(SourceTypeConvertor::class)
abstract class NewsDatabase:RoomDatabase() {
    companion object{
        const val NAME = "news.db"
    }

    abstract fun getNewsDao():NewsDataDao
}
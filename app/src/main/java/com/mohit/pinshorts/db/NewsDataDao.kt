package com.mohit.pinshorts.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mohit.pinshorts.entities.NewsResultEntity

@Dao
interface NewsDataDao {
    @Query("SELECT * FROM NEWSRESULTENTITY")
    fun getBookmarks(): List<NewsResultEntity>

    @Insert
    fun addBookmark(data:NewsResultEntity)

    @Query("Delete from NewsResultEntity where id=:id")
    fun deleteBookMark(id:Int)
}
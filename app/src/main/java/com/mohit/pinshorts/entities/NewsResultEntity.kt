package com.mohit.pinshorts.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson


@Entity
data class NewsResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val title: String,
    val source: Source?=null,
    val link: String?=null,
    val thumbnail: String?=null,
    val date :String?=null
)


class SourceTypeConvertor(){
    @TypeConverter
    fun fromSource(value: Source):String = Gson().toJson(value)
    @TypeConverter
    fun toSource(value: String):Source = Gson().fromJson(value , Source::class.java)
}
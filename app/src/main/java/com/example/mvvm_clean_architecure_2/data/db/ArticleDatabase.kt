package com.example.mvvm_clean_architecure_2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_clean_achitecture.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase() {
    abstract  fun getArticleDAO():ArticleDAO
}
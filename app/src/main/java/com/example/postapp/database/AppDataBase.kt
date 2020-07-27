package com.example.postapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.postapp.model.Article
import com.example.postapp.model.Media
import com.example.postapp.model.User

@Database(entities = [Article::class, Media::class, User::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
//    abstract fun MediaDao(): MediaDao
//    abstract fun UserDao(): UserDao

    companion object {
        var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}

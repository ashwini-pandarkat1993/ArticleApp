package com.example.postapp

import android.app.Application
import com.example.postapp.database.AppDataBase

class ArticleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDataBase.getAppDataBase(this);
    }
}
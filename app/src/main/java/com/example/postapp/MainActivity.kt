package com.example.postapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.postapp.database.AppDataBase
import com.example.postapp.view.ArticleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "articleDB"
        ).allowMainThreadQueries().build()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ArticleFragment.newInstance(1)).commit()
    }

}
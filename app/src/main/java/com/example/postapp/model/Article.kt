package com.example.postapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.postapp.database.DataConverter


@Entity
data class Article(
  @PrimaryKey(autoGenerate = true)
    val uId : Int,
    val id: Int,
    val createdAt: String,
    val content: String,
    val comments: Int,
    val likes: Int,
    @TypeConverters(DataConverter::class)
    val media: List<Media>,
    @TypeConverters(DataConverter::class)
    val user: List<User>
)

@Entity
data class Media(
    @PrimaryKey val id: Int,
    val blogId: Int,
    val createdAt: String,
    val image: String,
    val title: String,
    val url: String
)

@Entity
data class User(
    @PrimaryKey  val id: Int,
    val blogId: Int,
    val createdAt: String,
    val name: String,
    val avatar: String,
    val lastname: String,
    val city: String,
    val designation: String,
    val about: String
)
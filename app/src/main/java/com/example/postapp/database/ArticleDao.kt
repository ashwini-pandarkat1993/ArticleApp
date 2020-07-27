package com.example.postapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.postapp.model.Article


@Dao
interface ArticleDao {
    @Insert
   suspend fun interArticle(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getArticleList(): MutableList<Article>?

    @Query("DELETE FROM Article")
    suspend fun deleteArticle()
}

//@Dao
//interface MediaDao {
//    @Insert
//    fun insertMedia(media: Media)
//
//    @Query("SELECT * FROM Media")
//    fun getMedia(): List<Media>
//
//}
//
//@Dao
//interface UserDao {
//    @Insert
//    fun insertUser(user: User)
//
//    @Query("SELECT * FROM User")
//    fun getUsers(): List<User>
//
//}
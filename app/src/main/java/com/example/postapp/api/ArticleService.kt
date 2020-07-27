package com.example.postapp.api

import com.example.postapp.model.Article
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("blogs")
    fun hitCountCheck(@Query("page") page: String,
                      @Query("limit") limit: String):
            Observable<MutableList<Article>>


    companion object {
        fun create(): ArticleService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/")
                .build()

            return retrofit.create(ArticleService::class.java)
        }
    }
}

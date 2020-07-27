package com.example.postapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postapp.api.ArticleService
import com.example.postapp.database.AppDataBase
import com.example.postapp.database.ArticleDao
import com.example.postapp.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private var appDatabaseRef: AppDataBase? = null
    private var articleDao: ArticleDao? = null
    private val articleLiveData: MutableLiveData<MutableList<Article>> by lazy {
        MutableLiveData<MutableList<Article>>()
    }
    private val articleService by lazy {
        ArticleService.create()
    }
    private var disposable: Disposable? = null
    fun beginSearch() {
        fetchFromDB()
        disposable =
            articleService.hitCountCheck(page = "1", limit = "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> showResult(result) },
                    { error -> showError(error.message) }
                )
    }

    private fun fetchFromDB() {
        articleDao = appDatabaseRef?.articleDao()
        GlobalScope.launch(Dispatchers.Main) {
            articleDao?.getArticleList()?.let { showResult(it) }
        }
    }

    private fun showResult(result: MutableList<Article>) {
        if (result.isNotEmpty()) {
            articleDao = appDatabaseRef?.articleDao()
            articleLiveData.postValue(result)
            GlobalScope.launch(Dispatchers.Main) {
                articleDao?.deleteArticle()
                for (article: Article in result) {
                    articleDao?.interArticle(article)
                }
            }
        }
    }

    private fun showError(message: String?) {
    }

    fun getArticle(): MutableLiveData<MutableList<Article>> {
        return articleLiveData
    }

    fun setDBInstance(appDataBase: AppDataBase?) {
        appDatabaseRef = appDataBase
    }
}
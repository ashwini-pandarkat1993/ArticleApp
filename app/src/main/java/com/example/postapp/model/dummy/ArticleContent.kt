package com.example.postapp.model.dummy

import com.example.postapp.model.Article
import java.util.ArrayList
import java.util.HashMap


object ArticleContent {
    val ITEMS: MutableList<Article> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Article> = HashMap()

    private val COUNT = 25

     fun addItem(item: MutableList<Article>) {
        ITEMS.addAll(item)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

}
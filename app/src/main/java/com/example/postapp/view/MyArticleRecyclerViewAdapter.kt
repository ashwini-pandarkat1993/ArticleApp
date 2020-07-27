package com.example.postapp.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.postapp.R
import com.example.postapp.model.Article


class MyArticleRecyclerViewAdapter(
    private val values: MutableList<Article>,
    private val context: Context
) : RecyclerView.Adapter<MyArticleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.media[0].title
        holder.tvBody.text = item.content
        holder.tvComment.text = item.comments.toString()
        holder.tvLike.text = item.likes.toString()
        Glide
            .with(context)
            .load(item.media[0].image)
            .centerCrop()
            .into(holder.imageView);
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvComment: TextView = view.findViewById(R.id.tvLike)
        val tvLike : TextView = view.findViewById(R.id.tvComments)
        val tvTitle :TextView = view.findViewById(R.id.tvTitle)
        val tvBody : TextView = view.findViewById(R.id.tvBody)
        val imageView : ImageView = view.findViewById(R.id.imageView)

        override fun toString(): String {
            return super.toString() + " '" + tvBody.text + "'"
        }
    }
    fun updateList( articles: List<Article>) {
        values.addAll(articles)
        notifyDataSetChanged()
    }
}
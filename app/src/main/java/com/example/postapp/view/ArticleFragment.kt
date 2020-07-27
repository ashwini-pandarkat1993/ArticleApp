package com.example.postapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.R
import com.example.postapp.database.AppDataBase
import com.example.postapp.model.Article
import com.example.postapp.model.dummy.ArticleContent


class ArticleFragment : Fragment() {

    private var columnCount = 1
    lateinit var myArticleRecyclerViewAdapter: MyArticleRecyclerViewAdapter
    lateinit var  viewModel : ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        myArticleRecyclerViewAdapter = MyArticleRecyclerViewAdapter(
            ArticleContent.ITEMS,requireContext()
        )
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = myArticleRecyclerViewAdapter
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        viewModel.setDBInstance( AppDataBase.getAppDataBase(context = requireContext()))
        // Create the observer which updates the UI.
        val nameObserver = Observer<MutableList<Article>> {
            myArticleRecyclerViewAdapter.updateList(it)
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getArticle().observe(viewLifecycleOwner, nameObserver)
        viewModel.beginSearch()
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
package com.varunjain.baraka.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varunjain.baraka.R
import com.varunjain.baraka.ui.BaseConcatHolder
import kotlinx.android.synthetic.main.top_news_row.view.*
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
class BaseVerticalNewsConcatAdapter(private val context: Context,
                                    private val topNewsAdapter: NewsAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.top_news_row,parent,false)
        view.rvTopNews.layoutManager = LinearLayoutManager(context)
        view.progressBar.visibility = View.VISIBLE
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int  = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(topNewsAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(itemView: View): BaseConcatHolder<NewsAdapter>(itemView){
        override fun bind(adapter: NewsAdapter) {
            itemView.rvTopNews.adapter = adapter
        }
    }
}
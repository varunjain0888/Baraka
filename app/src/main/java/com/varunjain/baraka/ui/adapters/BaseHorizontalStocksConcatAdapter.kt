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
class BaseHorizontalStocksConcatAdapter(private val context: Context,
                                        private val stockAdapter: StocksAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val view = LayoutInflater.from(context).inflate(R.layout.top_news_row,parent,false)
        view.rvTopNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return ConcatViewHolder(view)
    }

    override fun getItemCount(): Int  = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(stockAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(itemView: View): BaseConcatHolder<StocksAdapter>(itemView){
        override fun bind(adapter: StocksAdapter) {
            itemView.rvTopNews.adapter = adapter
            itemView.progressBar.visibility = View.GONE
        }
    }
}
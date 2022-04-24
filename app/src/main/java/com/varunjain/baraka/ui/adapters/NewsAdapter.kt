package com.varunjain.baraka.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.varunjain.baraka.R
import com.varunjain.baraka.data.local.ArticleEntity
import kotlinx.android.synthetic.main.layout_news_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/22
 * Copyright: ADIB (2021-2022)
 */
class NewsAdapter: ListAdapter<ArticleEntity, NewsAdapter.ViewHolder>(NewsDC()) {
    val adapterScope = CoroutineScope(Dispatchers.Default)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_news_item, parent, false)
    )

    fun swapData(data: List<ArticleEntity>) = submitList(data.toMutableList())

        override fun onBindViewHolder(holder: ViewHolder, position: Int)=
                holder.bind(getItem(position))

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: ArticleEntity) = with(itemView) {
                tvTitle.text = item.title
                tvDesc.text = item.description
                tvdate.text = item.publishedAt?.split("T")?.get(0) ?: ""
                Picasso.get()
                        .load(item.urlToImage)
                        .into(ivImage)
            }

        }

        private class NewsDC : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(
                    oldItem: ArticleEntity,
                    newItem: ArticleEntity
            ): Boolean = oldItem.content == newItem.content

            override fun areContentsTheSame(
                    oldItem: ArticleEntity,
                    newItem: ArticleEntity
            ): Boolean = oldItem.content == newItem.content
        }

}
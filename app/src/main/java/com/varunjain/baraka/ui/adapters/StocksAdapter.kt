package com.varunjain.baraka.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.varunjain.baraka.R
import com.varunjain.baraka.models.Stock
import com.varunjain.baraka.utils.getRelativeTime
import kotlinx.android.synthetic.main.layout_stock_item.view.*
import java.util.*
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
class StocksAdapter : ListAdapter<Stock, StocksAdapter.ViewHolder>(StockDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_stock_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Stock>) = submitList(data.toMutableList())

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Stock) = with(itemView) {

            text_stock_name.text = item.name

            val currentPrice = "$${item.price}"
            text_current_price.text = currentPrice

            val prevPrice = "$${item.previousPrice}"
            text_prev_price.text = prevPrice

            when {
                item.price > item.previousPrice -> {
                    text_prev_price.setTextColor(resources.getColor(R.color.primaryGreen))
                    image_indicator.visibility = View.VISIBLE
                    image_indicator.setImageResource(R.drawable.ic_arrow_up)
                }
                item.price < item.previousPrice -> {
                    text_prev_price.setTextColor(resources.getColor(R.color.primaryRed))
                    image_indicator.visibility = View.VISIBLE
                    image_indicator.setImageResource(R.drawable.ic_arrow_down)
                }
                else -> {
                    text_prev_price.setTextColor(resources.getColor(R.color.grayDark))
                    image_indicator.visibility = View.GONE
                }
            }

            text_timestamp.text = Date(item.updatedAt).getRelativeTime()

        }

    }


    private class StockDC : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(
            oldItem: Stock,
            newItem: Stock
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Stock,
            newItem: Stock
        ): Boolean = oldItem == newItem
    }
}
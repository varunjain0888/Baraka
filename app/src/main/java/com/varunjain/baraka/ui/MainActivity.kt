package com.varunjain.baraka.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.varunjain.baraka.R
import com.varunjain.baraka.ui.adapters.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var contactAdapter : ConcatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val stockTickerAdapter = StocksAdapter()
        val topTrendingNewsAdapter = TopTrendingNewsAdapter()
        val newsAdapter = NewsAdapter()

        rv_main.layoutManager = LinearLayoutManager(this)

        viewModel.stocks.observe(this) {
            stockTickerAdapter.swapData(it)
        }

        viewModel.newsTrend.observe(this) {
            topTrendingNewsAdapter.swapData(it.subList(0,5))
            newsAdapter.swapData(it.subList(6,it.size))
        }

        contactAdapter = ConcatAdapter(BaseHorizontalStocksConcatAdapter(this, stockTickerAdapter),
                BaseHorizontalNewsConcatAdapter(this, topTrendingNewsAdapter),
                BaseVerticalNewsConcatAdapter(this, newsAdapter))

        rv_main.adapter = contactAdapter
    }
}
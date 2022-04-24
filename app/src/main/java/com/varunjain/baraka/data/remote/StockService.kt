package com.varunjain.baraka.data.remote

import android.content.Context
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.varunjain.baraka.models.ApiResult
import com.varunjain.baraka.models.IncPrices
import com.varunjain.baraka.models.ArticleDataItem
import com.varunjain.baraka.utils.readCsvFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.round

/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Singleton
class StockService @Inject constructor(
    private val csvMapper: CsvMapper,
    private val context : Context,
    private val gson: Gson
) {

    fun getStocks(): Flow<IncPrices> {
        return readCsvFile<IncPrices>(
            csvMapper,
            context.assets.open("stocks.csv")
        ).asFlow()
            .map { item ->
                item.copy(price = item.price?.times(1000)?.let { it1 -> round(it1) }?.div(1000))
            }
            .flowOn(Dispatchers.IO)
    }

    fun getTopTrendingNews() : List<ArticleDataItem> {
        val json_string = context.assets.open("news.json").bufferedReader().use{
            it.readText()
        }
        return jacksonObjectMapper()
            .readValue(json_string, ApiResult::class.java)
            .articles
    }
}
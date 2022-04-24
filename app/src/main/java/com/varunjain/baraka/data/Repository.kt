package com.varunjain.baraka.data

import com.varunjain.baraka.data.local.ArticleEntity
import com.varunjain.baraka.data.local.StockDatabase
import com.varunjain.baraka.data.remote.StockService
import com.varunjain.baraka.models.Stock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Singleton
class Repository @Inject constructor(
    private val service: StockService,
    private val db: StockDatabase
) {

    /**
     * Return Flowable Stream of Stocks from Database
     * Automatically emits on each DB update
     */
    fun getStocks(): Flow<List<Stock>> {
        return db.stockDao().getStocks()
    }

    /**
     * Fetch Stock Prices from Socket and Update DB
     * @param coroutineScope = Ideally ViewModelScope
     */
    fun refreshStocks(coroutineScope: CoroutineScope) {
        service.getStocks()
            .onEach {
                delay(1000)
                db.stockDao().insertOrUpdate(it)
            }
            .launchIn(coroutineScope)
    }

    fun getTopTrendingNews() : List<ArticleEntity> {
        return db.articleDao().getAllArticles()
    }

    suspend fun refreshNews() {

        db.articleDao().insertMapper(service.getTopTrendingNews())
    }
}
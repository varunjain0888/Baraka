package com.varunjain.baraka.data.local

import androidx.room.*
import com.varunjain.baraka.models.IncPrices
import com.varunjain.baraka.models.Stock
import kotlinx.coroutines.flow.Flow
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Dao
interface StockDao {


    @Query("SELECT * FROM stock_table WHERE name = :name")
    suspend fun get(name: String): Stock?


    // Limit return values till Paging
    @Query("SELECT * FROM stock_table ORDER BY name ASC LIMIT 50")
    fun getStocks(): Flow<List<Stock>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: Stock)


    /**
     * Updates stock prices if already present in DB
     * Inserts if no stock present (prev price = 0)
     */
    @Transaction
    suspend fun insertOrUpdate(item: IncPrices) {
        // Get Stock from DB
        val stockFromDb = get(item.name)

        val stockToInsert = if (stockFromDb != null) {
            // Update previous price
            Stock(
                name = item.name,
                price = item.price,
                previousPrice = stockFromDb.price
            )
        } else {
            Stock(
                name = item.name,
                price = item.price
            )
        }

        insert(stockToInsert)
    }

}
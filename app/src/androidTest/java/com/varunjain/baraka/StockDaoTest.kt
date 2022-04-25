package com.varunjain.baraka

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.varunjain.baraka.data.local.StockDatabase
import com.varunjain.baraka.models.IncPrices
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 25/04/2022
 * Copyright: ADIB (2021-2022)
 */
@RunWith(AndroidJUnit4::class)
class StockDaoTest {
    private var mDatabase: StockDatabase? = null
    @Before
    fun init() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
                StockDatabase::class.java
        ).build()
    }

    @After
    fun uninit() {
        mDatabase?.close()
    }

    @Test
    fun testLoadStockTicker() {
        runBlocking {
            mDatabase?.stockDao()?.insertOrUpdate(IncPrices(
                    name = "TESLA",
                    price = 126.24180880571902
            ))
            val stocks = mDatabase?.stockDao()?.getStocks()
            assertNotNull(stocks)
        }
    }
}
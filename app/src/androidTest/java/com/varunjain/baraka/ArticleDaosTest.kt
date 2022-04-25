package com.varunjain.baraka

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.varunjain.baraka.data.local.StockDatabase
import com.varunjain.baraka.models.ArticleDataItem
import com.varunjain.baraka.models.Source
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
class ArticleDaosTest {
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
    fun testLoadPopularArticles() {
        val articleEntities = mutableListOf<ArticleDataItem>()
        val source = Source(id="cnn", name = "CNN")
        articleEntities.add(ArticleDataItem(
            title = "News Today",
            url = "https://www.cnn.com/2022/04/21/politics/tribal-sovereignty-race-deconstructed-newsletter/index.html",
            publishedAt = "25/04/2022",
            source = source,
            author = "Varun Jain",
            content = "A version of this story appeared in CNNs Race Deconstructed newsletter. To get it in your inbox every week, sign up for free here.\n" +
                    "Native American leaders are again at odds with Oklahoma Republican … [+6769 chars]",
            description = "Republican Gov. Kevin Stitt's comment about a landmark US Supreme Court case is \"irresponsible\" and \"part of a larger push to eradicate tribal sovereignty in this country,\" Crystal Echo Hawk told CNN.",
            urlToImage = "https://media.cnn.com/api/v1/images/stellar/prod/220412130930-mcgirt-v-oklahoma-2021.jpg?c=16x9&q=w_800,c_fill"
        ))
        runBlocking {
            mDatabase?.articleDao()?.insertMapper(articleEntities)
            val articles = mDatabase?.articleDao()?.getAllArticles()
            assertNotNull(articles)
        }
    }
}
package com.varunjain.baraka.data.local

import androidx.room.*
import com.varunjain.baraka.models.ArticleDataItem
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
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Transaction
    suspend fun insertMapper(items: List<ArticleDataItem>) {
        items.forEach { item->
            val article = ArticleEntity(
                    title = item.title,
                    url = item.url,
                    author = item.author,
                    description = item.description,
                    urlToImage = item.urlToImage,
                    publishedAt = item.publishedAt,
                    content = item.content!!,
                    source = item.source?.name
            )
            insert(article)
        }
    }

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<ArticleEntity>

}
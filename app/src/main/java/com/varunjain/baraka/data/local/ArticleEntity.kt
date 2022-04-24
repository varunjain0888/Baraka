package com.varunjain.baraka.data.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Entity(tableName = "articles")
data class ArticleEntity(
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @PrimaryKey
    @NonNull
    @SerializedName("content")
    var content : String,
    @SerializedName("source")
    var source : String?
)

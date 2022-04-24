package com.varunjain.baraka.models

import com.google.gson.annotations.SerializedName
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
data class ApiResult(@SerializedName("status") val status: String,
                     @SerializedName("totalResults") val totalResults: Int,
                     @SerializedName("articles") val articles: List<ArticleDataItem>)
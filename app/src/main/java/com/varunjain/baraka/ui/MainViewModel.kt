package com.varunjain.baraka.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunjain.baraka.data.Repository
import com.varunjain.baraka.data.local.ArticleEntity
import com.varunjain.baraka.models.Stock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    val stocks = _stocks

    private val _news = MutableLiveData<List<ArticleEntity>>()
    val newsTrend = _news

    init {
        repository.getStocks()
            .onEach { _stocks.postValue(it) }
            .launchIn(viewModelScope)

        repository.refreshStocks(viewModelScope)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.refreshNews()
                _news.postValue(repository.getTopTrendingNews())
            }
        }
    }
}

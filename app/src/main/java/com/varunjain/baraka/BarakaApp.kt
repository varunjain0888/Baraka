package com.varunjain.baraka

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@HiltAndroidApp
class BarakaApp : Application(){
    companion object {
        lateinit var context : Context
    }



    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}
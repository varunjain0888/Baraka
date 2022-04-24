package com.varunjain.baraka.di

import android.content.Context
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.varunjain.baraka.BarakaApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Module
@InstallIn(ApplicationComponent::class)
object UtilityModule {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return BarakaApp.context
    }
    @Singleton
    @Provides
    fun provideCsvMapper() : CsvMapper{
        return CsvMapper().apply {
            registerModule(KotlinModule())
        }
    }
}
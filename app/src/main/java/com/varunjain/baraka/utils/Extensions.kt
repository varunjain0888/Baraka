package com.varunjain.baraka.utils

import android.text.format.DateUtils
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.InputStream
import java.util.*
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
fun Date.getRelativeTime(): String {
    val now = Date().time
    val difference = now - time

    val relativeTime = when {
        difference < 2000L -> "Just now"

        difference < DateUtils.MINUTE_IN_MILLIS -> DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.SECOND_IN_MILLIS
        )
        difference < DateUtils.HOUR_IN_MILLIS -> DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.MINUTE_IN_MILLIS
        )
        difference < DateUtils.DAY_IN_MILLIS -> DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.HOUR_IN_MILLIS
        )
        difference < DateUtils.WEEK_IN_MILLIS -> DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.DAY_IN_MILLIS
        )
        else -> DateUtils.getRelativeTimeSpanString(time, now, DateUtils.WEEK_IN_MILLIS)
    }

    return relativeTime.toString()
}

inline fun <reified T> readCsvFile(csvMapper: CsvMapper, fileName: InputStream): List<T> {
    return csvMapper
        .readerFor(T::class.java)
        .with(CsvSchema.emptySchema().withHeader())
        .readValues<T>(fileName)
        .readAll()
        .toList()
}
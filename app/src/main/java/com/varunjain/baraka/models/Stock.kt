package com.varunjain.baraka.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
@Entity(tableName = "stock_table")
data class Stock(

    @PrimaryKey(autoGenerate = false)
    val name: String,

    val price: Double,

    @ColumnInfo(name = "previous_price")
    val previousPrice: Double = 0.0,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()

) {

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Stock?

        if (name != other.name) return false
        if (price != other.price) return false
        if (previousPrice != other.previousPrice) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + previousPrice.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }

}
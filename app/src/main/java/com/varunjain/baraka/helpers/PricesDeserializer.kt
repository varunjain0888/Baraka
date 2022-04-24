package com.varunjain.baraka.helpers

import com.varunjain.baraka.models.IncPrices
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
/**
 * Description
 * <p>
 * Created by : Varun Jain
 * Email : varun.jain@adib.com
 * Date : 24/04/2022
 * Copyright: ADIB (2021-2022)
 */
class PricesDeserializer : JsonDeserializer<IncPrices> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): IncPrices {
        // Get both elements manually and create IncPrices

        val array = json?.asJsonArray
        return IncPrices(array?.get(0)?.asString ?: "MSFT", array?.get(1)?.asDouble ?: 0.0)
    }


}
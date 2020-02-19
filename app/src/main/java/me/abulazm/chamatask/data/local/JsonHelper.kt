package me.abulazm.chamatask.data.local

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.abulazm.chamatask.data.model.HeritageItem

class JsonHelper {

    fun parseHeritageItemsFromJson(jsonItemsString: String): List<HeritageItem>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            HeritageItem::class.java
        )
        val adapter: JsonAdapter<List<HeritageItem>> = moshi.adapter<List<HeritageItem>>(type)
        return adapter.fromJson(jsonItemsString)
    }

}
package me.abulazm.chamatask.data.database

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.abulazm.chamatask.data.model.HeritageItem

class HeritageItemInsertImpl(private val context: Context) : IInsert<HeritageItem> {

    override suspend fun insertItems(items: List<HeritageItem>) {
        withContext(Dispatchers.IO) {
            return@withContext HeritageDatabase.invoke(context).heritageDao().insertAll(items).size
        }
    }

}
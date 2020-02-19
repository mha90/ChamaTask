package me.abulazm.chamatask.data.local

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.abulazm.chamatask.data.database.HeritageDao
import me.abulazm.chamatask.data.model.HeritageItem

class HeritageItemsLocalDataSource(private val heritageDao: HeritageDao, private val pageSize: Int) :
    PositionalDataSource<HeritageItem>() {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    constructor(dao: HeritageDao) : this(dao, DEFAULT_PAGE_SIZE)

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<HeritageItem>) {
        coroutineScope.launch(Dispatchers.IO) {
            val index = params.startPosition / pageSize
            val list = heritageDao.getItemsInPages(pageSize, (index * pageSize))
            callback.onResult(list)
            Log.e("DataSource", "Loading Range: $index ")
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<HeritageItem>) {
        coroutineScope.launch(Dispatchers.IO) {
            val list = heritageDao.getItemsInPages(pageSize, 0)
            Log.e("DataSource", "Loading Initial")
            callback.onResult(list, 0)
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }

}


class HeritageItemsDataSourceFactory(private val dataSource: HeritageItemsLocalDataSource) :
    DataSource.Factory<Int, HeritageItem>() {

    override fun create(): DataSource<Int, HeritageItem> {
        return dataSource
    }

}
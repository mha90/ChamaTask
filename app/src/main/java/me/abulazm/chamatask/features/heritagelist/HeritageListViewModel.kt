package me.abulazm.chamatask.features.heritagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.abulazm.chamatask.data.database.HeritageDao
import me.abulazm.chamatask.data.local.HeritageItemsDataSourceFactory
import me.abulazm.chamatask.data.local.HeritageItemsLocalDataSource
import me.abulazm.chamatask.data.model.HeritageItem

class HeritageListViewModel(heritageDao: HeritageDao) : ViewModel() {

    val itemsLiveData: LiveData<PagedList<HeritageItem>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(5)
            .build()

        val dataSource = HeritageItemsLocalDataSource(heritageDao)
        val dataSourceFactory = HeritageItemsDataSourceFactory(dataSource)
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, config)
        itemsLiveData = livePagedListBuilder.build()
    }

}
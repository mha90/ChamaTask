package me.abulazm.chamatask.features.heritagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        livePagedListBuilder.setBoundaryCallback(callback())
        itemsLiveData = livePagedListBuilder.build()

    }

    private fun callback(): PagedList.BoundaryCallback<HeritageItem> {
        return object : PagedList.BoundaryCallback<HeritageItem>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                println("Zero Item Loaded")
            }

            override fun onItemAtEndLoaded(itemAtEnd: HeritageItem) {
                super.onItemAtEndLoaded(itemAtEnd)
                println("On Item Loaded End $itemAtEnd")
            }

            override fun onItemAtFrontLoaded(itemAtFront: HeritageItem) {
                super.onItemAtFrontLoaded(itemAtFront)
                println("On Item Loaded Front $itemAtFront")
            }
        }
    }

}
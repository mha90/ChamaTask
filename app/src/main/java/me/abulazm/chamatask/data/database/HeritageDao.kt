package me.abulazm.chamatask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.abulazm.chamatask.data.model.HeritageItem

@Dao
interface HeritageDao {
    @Query("SELECT * FROM `heritage` LIMIT :limit OFFSET :offset")
    fun getItemsInPages(limit: Int, offset: Int): List<HeritageItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(heritage: HeritageItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<HeritageItem>): List<Long>

}
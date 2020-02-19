package me.abulazm.chamatask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.abulazm.chamatask.data.model.HeritageItem

@Database(entities = [HeritageItem::class], version = 1, exportSchema = false)
abstract class HeritageDatabase : RoomDatabase() {

    abstract fun heritageDao(): HeritageDao

    companion object {
        @Volatile
        private var instance: HeritageDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): HeritageDatabase {
            return Room.databaseBuilder(context, HeritageDatabase::class.java, "heritage.db").build()
        }
    }

}
package me.abulazm.chamatask

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.abulazm.chamatask.data.database.HeritageDao
import me.abulazm.chamatask.data.database.HeritageDatabase
import me.abulazm.chamatask.data.model.HeritageItem
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ReadWriteEntityTest {

    private lateinit var heritageDao: HeritageDao
    private lateinit var db: HeritageDatabase

    @Before
    fun createDb() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, HeritageDatabase::class.java
        ).build()
        heritageDao = db.heritageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val heritageItem = HeritageItem(
            12, "14", 1997, "US", "Liberty Statue",
            "Liberty Statue", "", 2.0, 3.0
        )

        val id = heritageDao.insert(heritageItem)
        println("Id: $id")
//        val allItems = heritageDao.getAllItems()
//        println("Size: ${allItems.size}")
//        val firstItem = allItems[0]

//        assertThat(firstItem.id, equalTo(heritageItem.id))
//        assertThat(firstItem.itemId, equalTo(heritageItem.itemId))
    }

}
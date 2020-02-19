package me.abulazm.chamatask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heritage")
class HeritageItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int,
    val id: String,
    val year: Int,
    val target: String,
    val name: String,
    val shortInfo: String,
    val image: String,
    val lat: Double,
    val lng: Double
)
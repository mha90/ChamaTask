package me.abulazm.chamatask.data.database

interface IInsert<T> {
    suspend fun insertItems(items: List<T>)
}

package me.abulazm.chamatask.data.local

interface IInsert<T> {
    suspend fun insertItems(items: List<T>)
}

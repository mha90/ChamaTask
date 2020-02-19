package me.abulazm.chamatask.utils

import android.content.Context

const val PREF_NAME = "chama"
const val DATABASE_INIT_KEY_NAME = "db_init"

fun Context.isDatabaseInitialized(): Boolean {
    return getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean(DATABASE_INIT_KEY_NAME, false)
}

fun Context.isDatabaseInitialized(isDatabaseInitialized: Boolean) {
    getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        .putBoolean(DATABASE_INIT_KEY_NAME, isDatabaseInitialized).apply()
}
package me.abulazm.chamatask.utils

import android.content.Context
import android.widget.Toast


fun Context.toastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(textResId: Int) {
    Toast.makeText(this, this.getString(textResId), Toast.LENGTH_LONG).show()
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(textResId: Int) {
    Toast.makeText(this, this.getString(textResId), Toast.LENGTH_SHORT).show()
}
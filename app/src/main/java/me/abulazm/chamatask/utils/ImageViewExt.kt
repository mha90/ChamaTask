package me.abulazm.chamatask.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import me.abulazm.chamatask.R

fun ImageView.load(url: String) {
    Glide.with(this).load(url.trim()).placeholder(R.drawable.ic_placeholder).into(this)
}

fun ImageView.load(url: String, @DrawableRes placeHolder: Int, @DrawableRes error: Int) {
    Glide.with(this).load(url).placeholder(placeHolder).error(error).into(this)
}

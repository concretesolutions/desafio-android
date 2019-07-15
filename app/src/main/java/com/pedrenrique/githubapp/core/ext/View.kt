package com.pedrenrique.githubapp.core.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

fun ImageView.setRemoteImage(url: String, block: RequestBuilder<*>.() -> Unit = {}) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .apply(block)
        .into(this)
}
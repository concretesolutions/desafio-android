package com.joaoibarra.gitapp.extensions

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.joaoibarra.gitapp.GlideApp

fun ImageView.loadCircle(url: String?) {

    val requestOptions = RequestOptions()
    requestOptions.fitCenter()
    GlideApp.with(context)
            .asDrawable()
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(url)
            .into(this)

}

package com.joaoibarra.gitapp.extensions

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide



fun ImageView.loadCircle(url: String?) {
    val requestOptions = RequestOptions()
    requestOptions.fitCenter()
    Glide.with(context)
            .asDrawable()
            .apply(RequestOptions.circleCropTransform())
            .load(url)
            .into(this)
}
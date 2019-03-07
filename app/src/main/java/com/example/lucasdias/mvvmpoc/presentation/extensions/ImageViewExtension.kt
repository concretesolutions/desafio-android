package com.example.lucasdias.mvvmpoc.presentation.extensions

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.lang.Exception

fun ImageView.loadUrl(url: String?) {
    Picasso.get()
            .load(url)
            .into(this, object: Callback {
                override fun onSuccess() {
                    Timber.i("Success to load image: $url")
                }

                override fun onError(e: Exception?) {
                    Timber.i("Error to load image: $url")
                }
            })
}
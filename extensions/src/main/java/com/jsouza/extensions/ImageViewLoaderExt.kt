package com.jsouza.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jsouza.utils.Constants.Companion.IMAGE_MAX_HEIGHT
import com.jsouza.utils.Constants.Companion.IMAGE_MAX_WIDTH

private var errorAndPlaceHolderImage: Int = R.drawable.place_holder

fun ImageView.loadImageUrl(
    url: String? = null
) {
    val requestBuilder = setupGlide(this)

    requestBuilder
        .load(url)
        .into(this)
}

private fun setupGlide(
    imageView: ImageView,
    onLoadCompleted: () -> Unit = {},
    onError: () -> Unit = {}
): RequestBuilder<Drawable> {

    val requestBuilder = Glide.with(imageView)
        .`as`(Drawable::class.java)
        .override(IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT)
        .transition(GenericTransitionOptions.with(R.anim.fade_in))
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onError()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadCompleted()
                return false
            }
        })

    errorAndPlaceHolderImage.let {
        requestBuilder
            .placeholder(errorAndPlaceHolderImage)
            .override(IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT)
            .error(errorAndPlaceHolderImage)
    }

    return requestBuilder
}

package com.concrete.desafioandroid.data.loaders

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


@GlideModule
public final class ImageLoaderModule: AppGlideModule()

fun ImageView.loadImageCircle(context: Context, imageUrl: String?) {
    GlideApp.with(context)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    Log.d("GLIDE-EXCEPTION", e?.message)
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("GLIDE-EXCEPTION", "Load: ok")
                    return false
                }

            })
            .circleCrop()
            .into(this)
}
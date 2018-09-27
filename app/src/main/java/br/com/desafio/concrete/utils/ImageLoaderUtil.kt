package br.com.desafio.concrete.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Malkes on 26/09/2018.
 */

@GlideModule
open class ImageLoaderUtil : AppGlideModule() {

    companion object {
        fun load(context: Context, view: ImageView, url: String, requestOptions: RequestOptions = RequestOptions.noTransformation()){
            GlideApp
                    .with(context)
                    .load(url)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(view)

        }
    }
}
package com.rafael.fernandes.desafioconcrete.ui.custom.compoments

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions


class ImageViewUrl : androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setImageUrl(url: String) {
        Glide
            .with(this)
            .load(url)
            .transition(withCrossFade())
            .apply(
                RequestOptions
                    .circleCropTransform()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
            )
            .into(this)
    }
}

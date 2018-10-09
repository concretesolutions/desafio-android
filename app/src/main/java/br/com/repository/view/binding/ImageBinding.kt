package br.com.repository.view.binding

import android.content.res.Resources
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.Log
import android.widget.ImageView
import br.com.repository.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


object ImageBinding {

    @BindingAdapter("image")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Picasso.with(imageView.context).load(url)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .resize(200, 200)
                    .into(imageView, object : Callback {
                        override fun onSuccess() {
                            val bitmap: Bitmap = (imageView.drawable as BitmapDrawable).bitmap
                            val imageRoundedBitmap: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), bitmap)
                            imageRoundedBitmap.isCircular = true
                            val radius: Float = (Math.max(bitmap.width, bitmap.height) / 2.0F)
                            imageRoundedBitmap.cornerRadius = radius
                            imageView.setImageDrawable(imageRoundedBitmap)
                        }

                        override fun onError() {
                            imageView.setImageResource(R.drawable.ic_person_black_24dp)
                        }
                    })
        }
    }
}
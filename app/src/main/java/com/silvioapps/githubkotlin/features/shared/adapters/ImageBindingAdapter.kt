package com.silvioapps.githubkotlin.features.shared.adapters

import androidx.databinding.BindingAdapter
import android.widget.ImageView

import com.squareup.picasso.Picasso

@BindingAdapter("android:url")
fun setImage(imageView : ImageView, url : String?) {
    Picasso.with(imageView.context).load(url).into(imageView)
}


package com.example.desafioandroid.viewModel

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class ItemRepositoryViewModel: BaseObservable() {

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}
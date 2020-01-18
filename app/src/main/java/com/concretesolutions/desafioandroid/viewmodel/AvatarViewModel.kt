package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.model.Owner
import android.R
import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.widget.ImageView
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.with(view.context)
        .load(imageUrl)
        .resize(90, 90)
        .transform(CropCircleTransformation())
        .placeholder(R.drawable.star_big_off)
        .into(view)
}

class AvatarViewModel {
    val photoUrl: String
    val name: String
    val fullName: String

    constructor(owner: Owner) {
        photoUrl = owner.avatarUrl
        name = owner.login
        fullName = owner.login
    }

}


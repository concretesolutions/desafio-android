package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.model.Owner
import android.R
import com.squareup.picasso.Picasso
import android.databinding.BindingAdapter
import android.widget.ImageView
import jp.wasabeef.picasso.transformations.CropCircleTransformation

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


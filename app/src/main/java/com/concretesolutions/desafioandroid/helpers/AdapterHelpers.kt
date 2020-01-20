package com.concretesolutions.desafioandroid.helpers

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.concretesolutions.desafioandroid.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.with(view.context)
        .load(imageUrl)
        .resize(view.layoutParams.width, view.layoutParams.height)
        .transform(CropCircleTransformation())
        .placeholder(R.drawable.foto)
        .into(view)
}

@BindingAdapter("dataFormat")
fun dataCreated(view: TextView, strData: String?) {
    if (!strData.isNullOrEmpty()) {
        val isoDate = strData.split("T")[0]
        val arrDate = isoDate.split("-")

        view.text = "${arrDate[2]}/${arrDate[1]}/${arrDate[0]}"

    } else view.text = "Data não encontrada"
}
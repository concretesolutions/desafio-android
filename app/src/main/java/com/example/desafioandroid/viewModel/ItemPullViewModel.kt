package com.example.desafioandroid.viewModel

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.desafioandroid.schemas.PullItem
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*


class ItemPullViewModel(var mPullItem:PullItem): BaseObservable() {

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: CircleImageView, url:String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    fun pullDate():String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        val parse = format.parse(mPullItem.createdAt)

        val formatResult = SimpleDateFormat("dd/MM/yyy HH:mm", Locale.US)
        return formatResult.format(parse)

    }

    fun pullState():String{
        return mPullItem.number.toString()
    }


}
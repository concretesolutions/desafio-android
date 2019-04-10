package com.example.desafioandroid.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.desafioandroid.schemas.PullItem
import de.hdodenhof.circleimageview.CircleImageView


class ItemPullViewModel(var mPullItem:PullItem): BaseObservable() {

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: CircleImageView, url:String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    fun pullTitle():String{
        return mPullItem.title!!
    }

    fun username():String{
        return mPullItem.user!!.login!!
    }

    fun pullDescription():String{
        return mPullItem.body!!
    }

    fun pullDate():String{
        return mPullItem.createdAt!!
    }

    fun pictureProfile():String{
        return mPullItem.user!!.avatarUrl!!
    }

    fun pullState():String{
        return mPullItem.state!!
    }


}
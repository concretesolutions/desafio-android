package com.example.desafioandroid.viewModel

import android.content.Context
import android.databinding.BaseObservable
import com.example.desafioandroid.schemas.PullItem

class ItemPullViewModel(private val mPullItem:PullItem, private val mContext: Context): BaseObservable() {

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
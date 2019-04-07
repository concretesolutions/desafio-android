package com.example.desafioandroid.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import com.example.desafioandroid.schemas.RepositoryItem
import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import de.hdodenhof.circleimageview.CircleImageView


class ItemRepositoryViewModel(private val mRepositoryItem: RepositoryItem, private val context: Context): BaseObservable() {
    val TAG = javaClass.name

    fun onItemClick(view: View) {
        Log.e(TAG, "onItemClick")
    }

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: CircleImageView, url:String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    fun pictureProfile (): String {
        return mRepositoryItem.owner!!.avatarUrl!!
    }

    fun username(): String{
        return mRepositoryItem.owner!!.login!!
    }

    fun titleRepository(): String{
        return mRepositoryItem.name!!
    }

    fun descriptionRepository(): String{
        return mRepositoryItem.description!!
    }

    fun cantFork(): String{
        return mRepositoryItem.forksCount.toString()
    }

    fun cantWatcher(): String{
        return mRepositoryItem.watchersCount.toString()
    }

    fun cantLike():String{
        return mRepositoryItem.stargazersCount.toString()
    }

}
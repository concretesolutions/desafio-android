package com.example.desafioandroid.viewModel.itemAdapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.desafioandroid.schema.PullItem
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*


class ItemPullViewModel(var mPullItem:PullItem): BaseObservable() {
    val TAG = javaClass.simpleName

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

    fun onItemClick(view: View) {
        Log.i(TAG,mPullItem.htmlUrl)
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mPullItem.htmlUrl))
        view.context.startActivity(browserIntent)
    }


}
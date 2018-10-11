package com.diegoferreiracaetano.github.biding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.github.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(boolean: Boolean) {
        visibility = if(boolean) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("setLoad")
    fun SwipeRefreshLayout.setLoad(networkState: NetworkState?) {
        if(networkState?.status == NetworkState.LOADING.status){
            isRefreshing = true
        }else{
            isRefreshing = false
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?) {
        if(!url.isNullOrEmpty()) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(context.getDrawable(R.drawable.ic_account))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, throwable: Throwable?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        throwable?.let {
            val snackbar = Snackbar.make(view, "Error load", Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }
}
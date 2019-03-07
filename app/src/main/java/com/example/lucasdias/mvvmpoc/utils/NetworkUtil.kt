package com.example.lucasdias.mvvmpoc.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    fun isNetworkConnected(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return (connectivityManager?.activeNetworkInfo?.isConnected) ?: false
    }
}
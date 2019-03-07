package com.example.lucasdias.mvvmpoc.utils

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkReceiver(private val networkStatusListener: NetworkStatusListener) : BroadcastReceiver()  {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        val isConnected = NetworkUtil.isNetworkConnected(context)
        networkStatusListener.onNetworkStatusChanged(isConnected)
    }


    interface NetworkStatusListener {
        fun onNetworkStatusChanged(isConnected: Boolean)
    }
}
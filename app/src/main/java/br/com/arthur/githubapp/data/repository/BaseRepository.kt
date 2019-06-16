package br.com.arthur.githubapp.data.repository

import android.content.Context
import android.net.ConnectivityManager

open class BaseRepository(private val context: Context) {

    protected fun checkConnection(): Boolean {
        val isConnected: Boolean
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isConnected =
            (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isAvailable
                    && connectivityManager.activeNetworkInfo.isConnected)
        return isConnected
    }

}
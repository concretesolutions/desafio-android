package br.com.desafio.concrete.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Malkes on 26/09/2018.
 */
object NetworkUtils {

    fun isInternetAvailable(context: Context?): Boolean {
        context?.let {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val info: NetworkInfo? = connectivityManager.activeNetworkInfo

            return info?.isConnected == true
        }

        return false
    }

}
package cl.mauledev.github.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager

class ConnectionUtils {

    companion object {

        fun isConnected(context: Context?): Boolean {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val info = connectivityManager.activeNetworkInfo

            return info != null && info.isConnected && isConnectionFast(info.type, info.subtype)
        }

        private fun isConnectionFast(type: Int, subType: Int): Boolean {
            return when (type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> when (subType) {
                    TelephonyManager.NETWORK_TYPE_1xRTT -> false
                    TelephonyManager.NETWORK_TYPE_CDMA -> false
                    TelephonyManager.NETWORK_TYPE_EDGE -> false
                    TelephonyManager.NETWORK_TYPE_EVDO_0 -> true
                    TelephonyManager.NETWORK_TYPE_EVDO_A -> true
                    TelephonyManager.NETWORK_TYPE_GPRS -> false
                    TelephonyManager.NETWORK_TYPE_HSDPA -> true
                    TelephonyManager.NETWORK_TYPE_HSPA -> true
                    TelephonyManager.NETWORK_TYPE_HSUPA -> true
                    TelephonyManager.NETWORK_TYPE_UMTS -> true
                    TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                    TelephonyManager.NETWORK_TYPE_LTE -> true
                    else -> false
                }
                else -> false
            }
        }
    }
}
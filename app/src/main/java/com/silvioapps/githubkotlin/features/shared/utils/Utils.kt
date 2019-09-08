package com.silvioapps.githubkotlin.features.shared.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.security.ProviderInstaller
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener
import javax.net.ssl.SSLContext

class Utils {
    companion object{
        fun setTags(position : Int, view : View) {
            if (view is ViewGroup) {
                for (index in 0..view.getChildCount()) {
                    val nextChild : View? = view.getChildAt(index)
                    nextChild?.setTag(position)

                    if (nextChild is ViewGroup) {
                        setTags(position, nextChild)
                    }
                }
            }
        }

        fun setClickListeners(view : View, viewClickListener : ViewClickListener?) {
            if (view is ViewGroup) {
                for (index in 0..view.getChildCount()) {
                    val nextChild : View? = view.getChildAt(index)
                    nextChild?.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v : View) {
                            if (nextChild.getTag() != null) {
                                viewClickListener?.onClick(nextChild.context, nextChild, nextChild.getTag() as Int)
                            }
                        }
                    });

                    if (nextChild is ViewGroup) {
                        setClickListeners(nextChild, viewClickListener)
                    }
                }
            }
        }

        fun fixSSLError(context : Context){
            try {
                ProviderInstaller.installIfNeeded(context)
                val sslContext = SSLContext.getInstance("TLSv1.2")
                sslContext.init(null, null, null)
                sslContext.createSSLEngine()
            }
            catch(e : Exception){
                e.printStackTrace()
            }
        }
    }
}

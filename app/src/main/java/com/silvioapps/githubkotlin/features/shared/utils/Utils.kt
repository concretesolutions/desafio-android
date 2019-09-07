package com.silvioapps.githubkotlin.features.shared.utils

import android.view.View
import android.view.ViewGroup
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener

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
    }
}

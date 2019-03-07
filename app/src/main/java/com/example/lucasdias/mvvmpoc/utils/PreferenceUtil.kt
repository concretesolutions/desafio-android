package com.example.lucasdias.mvvmpoc.utils

import android.content.Context
import android.preference.PreferenceManager
import com.example.lucasdias.mvvmpoc.utils.Constants.PAGE


object PreferenceUtil {

    fun setPage(page:Int, context: Context){
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor.putInt(PAGE, page)
            editor.apply()
    }

    fun getPage(context: Context): Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PAGE, 0)
    }
}
package com.hotmail.fignunes.desafioconcretesolution.presentation.common

import android.content.Context

class StringHelper(val context: Context) {
    fun getString(id: Int, vararg params: Any): String {
        return context.getString(id, *params)
    }
}
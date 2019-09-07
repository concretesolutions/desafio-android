package com.silvioapps.githubkotlin.features.shared.listeners

import android.content.Context
import android.view.View

interface ViewClickListener {
    fun onClick(context : Context, view : View, position : Int)
}

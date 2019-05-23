package com.uharris.desafio_android.presentation.base

import android.content.Context
import com.uharris.desafio_android.utils.networkInfo
import javax.inject.Inject

class NetworkHandler @Inject constructor(private val context: Context) {
    open val isConnected get() = context.networkInfo?.isConnected
}
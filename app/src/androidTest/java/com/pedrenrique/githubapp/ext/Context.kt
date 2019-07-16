package com.pedrenrique.githubapp.ext

import android.content.Context
import okio.Buffer
import java.io.IOException

fun Context.readAsset(assetPath: String): Buffer =
    try {
        assets.open("network_mocks/$assetPath")
            .use {
                val size = it.available()
                val bytes = ByteArray(size).apply { it.read(this) }
                Buffer().write(bytes)
            }
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
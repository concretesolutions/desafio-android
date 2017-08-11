@file:JvmName("CacheHandler")

package br.com.concrete.sdk.handler

import java.lang.System.currentTimeMillis

internal data class Cache<out T>(val timeStamp: Long = currentTimeMillis(),
                                 val data: T)

internal fun <T> Cache<T>.isValid() = currentTimeMillis() - timeStamp < 5000 //3600000 // 1 Hour
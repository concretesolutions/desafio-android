package br.com.concrete.sdk.data.remote.exception

import java.lang.Exception

open class SdkException internal constructor(message: String, val code: Int, val requestedPath: String) : Exception(message)
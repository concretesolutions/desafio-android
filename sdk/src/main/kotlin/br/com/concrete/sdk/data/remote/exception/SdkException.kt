package br.com.concrete.sdk.data.remote.exception

open class SdkException internal constructor(message: String, val code: Int, val requestedPath: String) : Exception(message)
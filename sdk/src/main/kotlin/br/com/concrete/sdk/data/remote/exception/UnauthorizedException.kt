package br.com.concrete.sdk.data.remote.exception

class UnauthorizedException internal constructor(message: String, code: Int, requestedPath: String) : SdkException(message, code, requestedPath)
package br.com.concrete.sdk.data.remote.exception

class ServerException internal constructor(message: String, code: Int, requestedPath: String) : SdkException(message, code, requestedPath)
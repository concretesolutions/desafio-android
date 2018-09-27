package br.com.concrete.desafio.data.exception

class UnauthorizedException internal constructor(message: String, code: Int, requestedPath: String) : SdkException(message, code, requestedPath)
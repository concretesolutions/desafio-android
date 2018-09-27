package br.com.concrete.desafio.data.exception

class BadRequestException internal constructor(message: String, code: Int, requestedPath: String) : SdkException(message, code, requestedPath)
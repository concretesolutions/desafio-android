package br.com.concrete.desafio.data.exception

import java.lang.Exception

open class SdkException internal constructor(message: String, val code: Int, val requestedPath: String) : Exception(message)
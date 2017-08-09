@file:JvmName("ResponseUtils")

package br.com.concrete.sdk.extension

import br.com.concrete.sdk.handler.Response
import br.com.concrete.sdk.model.type.ERROR
import br.com.concrete.sdk.model.type.ResponseStatus

internal fun <T> T?.toDataResponse(@ResponseStatus status: Long) = Response(this, null, status)

internal fun <T> T?.toDataResponseWithError(error: Throwable) = Response(this, error, ERROR)

internal fun Throwable.toErrorResponse() = Response(null, this, ERROR)
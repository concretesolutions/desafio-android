@file:JvmName("ResponseUtils")

package br.com.concrete.desafio.data.extension

import br.com.concrete.desafio.data.model.DataResult
import br.com.concrete.desafio.data.model.enum.DataResultStatus

internal fun loadingResponse() = DataResult(null, null, DataResultStatus.LOADING)

internal fun <T> T?.toDataResponse(status: DataResultStatus) = DataResult(this, null, status)

internal fun <T> T?.toDataResponseWithError(error: Throwable) = DataResult(this, error, DataResultStatus.ERROR)

internal fun <T> Throwable.toErrorResponse() = DataResult<T>(null, this, DataResultStatus.ERROR)
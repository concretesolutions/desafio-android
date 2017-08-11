package br.com.concrete.sdk.model

import br.com.concrete.sdk.model.type.DataResultStatus

data class DataResult<out T>(
        val data: T?,
        var error: Throwable?,
        @get:DataResultStatus
        @DataResultStatus val status: Long
)
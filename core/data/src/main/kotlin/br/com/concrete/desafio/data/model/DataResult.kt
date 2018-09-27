package br.com.concrete.desafio.data.model

import br.com.concrete.desafio.data.model.enum.DataResultStatus

data class DataResult<out T>(
        val data: T?,
        var error: Throwable?,
        val status: DataResultStatus
)
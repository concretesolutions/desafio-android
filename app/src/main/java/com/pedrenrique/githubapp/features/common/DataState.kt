package com.pedrenrique.githubapp.features.common

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.ext.append
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.model.*

sealed class DataState(val data: List<ModelHolder>) {
    constructor(vararg data: ModelHolder) : this(data.toList())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataState

        if (data != other.data) return false

        return true
    }

    override fun hashCode() = data.hashCode()

    object Pending : DataState(LoadingModelHolder())
    data class NextPending(val lastData: List<ModelHolder>) :
        DataState(lastData.append(LoadingItemModelHolder()))

    data class Failed(val error: Throwable) : DataState(ErrorModelHolder(Failure.Full(error)))

    data class NextFailed(val error: Throwable, val lastData: List<ModelHolder>) :
        DataState(lastData.append(ErrorItemModelHolder(Failure.Item(error))))

    class Loaded(data: List<ModelHolder>) : DataState(data)

    class Completed(data: List<ModelHolder>) : DataState(data)
    object Empty : DataState(EmptyModelHolder(Failure.Empty))
}
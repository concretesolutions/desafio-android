package com.pedrenrique.githubapp.features.repositories

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.ext.append
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.features.common.model.*

sealed class RepositoriesState(val data: List<ModelHolder>) {
    object Pending : RepositoriesState(listOf(LoadingModelHolder()))
    data class NextPending(val original: List<ModelHolder>) :
        RepositoriesState(original.append(LoadingItemModelHolder()))

    data class Failed(val error: Throwable) :
        RepositoriesState(listOf(
            ErrorModelHolder(
                Failure.Full(error)
            )
        ))

    data class NextFailed(val error: Throwable, val original: List<ModelHolder>) :
        RepositoriesState(original.append(
            ErrorItemModelHolder(
                Failure.Item(error)
            )
        ))

    class Loaded(data: List<ModelHolder>) : RepositoriesState(data)
    class Completed(data: List<ModelHolder>) : RepositoriesState(data)
    object Empty : RepositoriesState(listOf(EmptyModelHolder()))
}
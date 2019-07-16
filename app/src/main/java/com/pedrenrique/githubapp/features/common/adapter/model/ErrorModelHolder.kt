package com.pedrenrique.githubapp.features.common.adapter.model

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

data class ErrorModelHolder(val error: Failure.Full) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(error)
}
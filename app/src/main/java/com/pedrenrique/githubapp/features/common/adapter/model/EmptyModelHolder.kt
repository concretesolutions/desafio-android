package com.pedrenrique.githubapp.features.common.adapter.model

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

data class EmptyModelHolder(val empty: Failure.Empty) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(empty)
}
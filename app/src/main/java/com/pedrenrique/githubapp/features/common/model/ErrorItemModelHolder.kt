package com.pedrenrique.githubapp.features.common.model

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory

class ErrorItemModelHolder(val error: Failure.Item) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(error)
}
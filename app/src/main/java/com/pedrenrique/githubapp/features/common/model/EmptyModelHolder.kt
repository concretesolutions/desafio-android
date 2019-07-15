package com.pedrenrique.githubapp.features.common.model

import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory

class EmptyModelHolder : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(Failure.Empty)
}
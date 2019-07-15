package com.pedrenrique.githubapp.features.common.model

import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory

class LoadingModelHolder : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(Loading.Full)
}
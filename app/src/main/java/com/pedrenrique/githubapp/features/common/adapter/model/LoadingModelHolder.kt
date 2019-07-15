package com.pedrenrique.githubapp.features.common.adapter.model

import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

class LoadingModelHolder : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(Loading.Full)
}
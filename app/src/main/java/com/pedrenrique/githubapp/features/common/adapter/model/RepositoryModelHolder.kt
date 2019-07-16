package com.pedrenrique.githubapp.features.common.adapter.model

import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

data class RepositoryModelHolder(val repo: Repository) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(repo)
}
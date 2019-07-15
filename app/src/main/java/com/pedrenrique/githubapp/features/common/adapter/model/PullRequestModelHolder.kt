package com.pedrenrique.githubapp.features.common.adapter.model

import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

class PullRequestModelHolder(val pr: PullRequest) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(pr)
}
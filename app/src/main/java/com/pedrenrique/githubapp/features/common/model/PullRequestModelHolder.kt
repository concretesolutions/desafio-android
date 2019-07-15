package com.pedrenrique.githubapp.features.common.model

import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory

class PullRequestModelHolder(val pr: PullRequest) : ModelHolder() {
    override fun type(typesFactory: TypesFactory) =
        typesFactory.type(pr)
}
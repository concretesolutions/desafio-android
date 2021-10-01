package com.concrete.challenge.presentation

import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.presentation.model.RepositoryItem

fun RepositoryEntity.toRepositoryItem() = RepositoryItem(

    repositoryId = repositoryId,
    repositoryName = repositoryName,
    repositoryDescription = repositoryDescription,
    repositoryOwner = repositoryOwner,
    starsAmount = starsAmount,
    forksAmount = forksAmount,

    openPullRequestAmount = openPullRequestAmount,
    closedPullRequestAmount = closedPullRequestAmount

)
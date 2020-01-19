package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.helpers.StringHelper
import com.concretesolutions.desafioandroid.model.Repository

class RepositoryViewModel {
    val repositoryData: Repository
    val countInfoViewModel: CountInfoViewModel
    val headerViewModel: HeaderViewModel
    val avatarViewModel: AvatarViewModel

    constructor(repository: Repository) {
        repositoryData = repository
        avatarViewModel = AvatarViewModel(repository.owner)
        headerViewModel = HeaderViewModel(repository.name, StringHelper.makeShortDescription(repository.description))
        countInfoViewModel = CountInfoViewModel(repository.forksCount.toString(), repository.starsCount.toString())
    }
}
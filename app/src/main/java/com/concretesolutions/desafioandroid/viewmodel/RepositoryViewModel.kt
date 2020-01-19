package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.model.Repository

class RepositoryViewModel {
    val repositoryData: Repository
    val countInfoViewModel: CountInfoViewModel
    val headerViewModel: HeaderViewModel
    val avatarViewModel: AvatarViewModel

    constructor(repository: Repository) {
        repositoryData = repository
        avatarViewModel = AvatarViewModel(repository.owner)
        val description = if( repository.description.isNullOrEmpty() ) "--" else repository.description!!
        headerViewModel = HeaderViewModel(repository.name, description)
        countInfoViewModel = CountInfoViewModel(repository.forksCount.toString(), repository.starsCount.toString())
    }
}
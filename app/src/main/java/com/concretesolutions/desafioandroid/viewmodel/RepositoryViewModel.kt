package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.model.Repository

class RepositoryViewModel {
    val countInfoViewModel: CountInfoViewModel
    val headerViewModel: HeaderViewModel
    val avatarViewModel: AvatarViewModel

    constructor(repository: Repository) {
        avatarViewModel = AvatarViewModel(repository.owner)
        headerViewModel = HeaderViewModel(repository.name, repository.description)
        countInfoViewModel = CountInfoViewModel(repository.forksCount.toString(), repository.starsCount.toString())
    }
}
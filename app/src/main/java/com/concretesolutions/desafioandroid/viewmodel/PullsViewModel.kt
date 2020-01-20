package com.concretesolutions.desafioandroid.viewmodel

import com.concretesolutions.desafioandroid.helpers.StringHelper
import com.concretesolutions.desafioandroid.model.PullRequest

class PullsViewModel {
    val pullRequest: PullRequest
    val headerViewModel: HeaderViewModel
    val avatarViewModel: AvatarViewModel

    constructor(pull: PullRequest) {
        pullRequest = pull
        avatarViewModel = AvatarViewModel(pull.user)
        headerViewModel = HeaderViewModel(pull.title, StringHelper.prepareDescription(pull.body))
    }
}
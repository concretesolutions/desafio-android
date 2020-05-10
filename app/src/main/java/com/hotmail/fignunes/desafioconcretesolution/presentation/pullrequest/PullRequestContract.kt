package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest

import com.hotmail.fignunes.desafioconcretesolution.model.PullRequest

interface PullRequestContract {
    fun initializeAdapter(pullRequests: List<PullRequest>)
    fun message(error: Int)
    fun initializeSwipe()
    fun swipeOff()
}
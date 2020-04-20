package com.example.githubtest.features.pullrequest

import com.example.githubtest.data.model.PullRequest

interface PullRequestClickListener {
    fun onClick(pullRequest: PullRequest)
}
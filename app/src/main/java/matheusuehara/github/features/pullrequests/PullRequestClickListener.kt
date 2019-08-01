package matheusuehara.github.features.pullrequests

import matheusuehara.github.data.model.PullRequest

interface PullRequestClickListener {
    fun onClick(pullRequest: PullRequest)
}
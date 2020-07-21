package com.jsouza.repodetail.utils

import com.jsouza.repodetail.domain.model.PullRequests

class PullRequestCalculator {

    companion object {
        private const val OPEN_PULL_REQUEST_STATUS = "open"
        private const val CLOSED_PULL_REQUEST_STATUS = "closed"

        fun calculateOpenedPullRequestsCount(
            pullRequests: List<PullRequests>
        ): Int {
            val openedPullsList = arrayListOf<Int>()
            for (element in pullRequests) {
                element.state.let { pullRequestStatus ->
                    when (pullRequestStatus) {
                        OPEN_PULL_REQUEST_STATUS -> openedPullsList.add(
                            Constants.ONE_ITEM
                        )
                        else -> {}
                    }
                }
            }
            return openedPullsList.sum()
        }

        fun calculateClosedPullRequestsCount(
            pullRequests: List<PullRequests>
        ): Int {
            val closedPullsList = arrayListOf<Int>()
            for (element in pullRequests) {
                element.state.let { pullRequestStatus ->
                    when (pullRequestStatus) {
                        CLOSED_PULL_REQUEST_STATUS -> closedPullsList.add(
                            Constants.ONE_ITEM
                        )
                        else -> {}
                    }
                }
            }
            return closedPullsList.sum()
        }
    }
}

package com.bassul.mel.app.feature

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

class convertResponseToObject {

    companion object {
        fun convertPullRequestListResponseToPullResponse(pullRequestList: List<PullRequestListResponse>): ArrayList<PullRequest> {
            val pullRequests: ArrayList<PullRequest> = arrayListOf()
            pullRequestList.forEach {
                val pr = PullRequest(
                    it.html_url,
                    it.updated_at,
                    it.body,
                    it.user.login,
                    it.user.avatar_url
                )
                pullRequests.add(pr)
            }
            return pullRequests
        }

        fun convertGithubRepositoriesListResponseToRepositoriesList(listResponse: RepositoriesListResponse): ArrayList<Item> {
            val items: ArrayList<Item> = arrayListOf()

            listResponse.items.forEach {
                val repository = Item(
                    it.id,
                    it.name,
                    it.owner,
                    it.stargazers_count,
                    it.forks_count,
                    it.description,
                    it.pulls_url
                )
                items.add(repository)
            }
            return items
        }
    }

}
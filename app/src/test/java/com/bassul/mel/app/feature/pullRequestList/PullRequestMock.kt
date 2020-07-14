package com.bassul.mel.app.feature.pullRequestList

import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse
import com.bassul.mel.app.feature.pullRequestsList.repository.model.UserResponse

class PullRequestMock {
    companion object{
        fun listPullRequestResponseMock() : List<PullRequestListResponse>{
            var list : ArrayList<PullRequestListResponse> = ArrayList()
            list.add(pullRequestResponseMock())
            return list
        }

        fun pullRequestResponseMock(): PullRequestListResponse {
            return PullRequestListResponse("url","update", "body", userMock())
        }

        fun pullRequestListMock() : ArrayList<PullRequest>{
            var list : ArrayList<PullRequest> = ArrayList()
            list.add(pullRequestMock())
            return list
        }

        fun userMock() : UserResponse {
            return UserResponse("login", "avatar")
        }

        fun pullRequestMock() : PullRequest {
            return PullRequest("url", "update", "body", userMock().login, userMock().avatar_url)
        }

        fun errorMessagePullRequestMock() : Int{
            return 1
        }
    }
}
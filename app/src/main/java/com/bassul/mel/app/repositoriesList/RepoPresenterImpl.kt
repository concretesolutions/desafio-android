package com.bassul.mel.app.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import kotlin.collections.ArrayList

class RepoPresenterImpl (val view: RepositoriesListContract.View) : RepositoriesListContract.Presenter {

    override fun showCard(ItemModel: ArrayList<Item>) {
        return view.showCard(ItemModel)
    }

    override fun openListPullRequest(pullRequest: ArrayList<PullRequest>) {
        return view.openActivityPullRequest(pullRequest)
    }

}
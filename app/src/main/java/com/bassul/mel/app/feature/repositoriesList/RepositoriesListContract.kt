package com.bassul.mel.app.feature.repositoriesList

import androidx.annotation.StringRes
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest

interface RepositoriesListContract{

    interface View{
        fun initRecyclerView()
        fun initRepositoriesCard()
        fun showCard(repositories: ArrayList<Item>)
        fun openActivityPullRequest(pullRequest: ArrayList<PullRequest>)
        fun showErrorCard(@StringRes errorMessage: Int)
        fun showErrorPullRequestCard(@StringRes errorPullRequest: Int)
    }

    interface Presenter{
        fun showCard(ItemModel: ArrayList<Item>)
        fun openListPullRequest(pullRequest: ArrayList<PullRequest>)
        fun errorShowCard(@StringRes errorMessage: Int)
        fun errorShowPullRequestCard(@StringRes errorPullRequest: Int)
    }

    interface Interactor{
        fun loadRepositories(pages : Int)
        fun getSelectedItem(item: Item)
    }

    interface Repository{
        fun readRepositoryJson(pages : Int, callback: RepositotyAllRepositoriesCallback)
        fun readPullRequestJson(login : String, nameRepository : String, callback: RepositotySelectedRepositoriesCallback)
    }
}
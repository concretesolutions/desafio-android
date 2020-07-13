package com.bassul.mel.app.interactor

import android.util.Log
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.repositoriesList.repository.model.PullRequestListResponse

class RepoInteractorImpl (
    val presenter: RepositoriesListContract.Presenter,
    val repository: RepositoriesListContract.Repository
) : RepositoriesListContract.Interactor {

    override fun loadRepositories(pages : Int){

        return repository.readRepositoryJson(pages, object : RepositotyAllRepositoriesCallback {
            override fun onSuccess(repositoriesList: RepositoriesListResponse) {
                val repositories : ArrayList<Item> = convertGithubRepositoriesListResponseToRepositoriesList(repositoriesList)
                presenter.showCard(repositories)
            }
        })
    }

    override fun getSelectedItem(item: Item) {
        repository.readPullRequestJson(item.owner.login, item.name, object : RepositotySelectedRepositoriesCallback{
            override fun onSuccess(pullRequestList: List<PullRequestListResponse>) {
                presenter.openListPullRequest(convertPullRequestListResponseToPullResponse(pullRequestList))
            }

        })
    }

    private fun convertPullRequestListResponseToPullResponse(pullRequestList: List<PullRequestListResponse>) : ArrayList<PullRequest>{
        val pullRequests : ArrayList<PullRequest> = arrayListOf()
        pullRequestList.forEach{
            val pr = PullRequest(it.html_url,
                it.updated_at,
                it.body,
                it.user.login,
                it.user.avatar_url)
            pullRequests.add(pr)
        }
        return pullRequests
    }

    private fun convertGithubRepositoriesListResponseToRepositoriesList(listResponse: RepositoriesListResponse) : ArrayList<Item>{
        val items : ArrayList<Item> = arrayListOf()

        listResponse.items.forEach{
            val repository = Item(it.id,
                it.name,
                it.owner,
                it.stargazers_count,
                it.forks_count,
                it.description,
                it.pulls_url)
            items.add(repository)
        }
        return items
    }
}
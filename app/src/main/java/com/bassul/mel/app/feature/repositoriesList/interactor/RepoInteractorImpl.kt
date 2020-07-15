package com.bassul.mel.app.feature.repositoriesList.interactor

import com.bassul.mel.app.R
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

class RepoInteractorImpl(
    val presenter: RepoListContract.Presenter,
    val repository: RepoListContract.Repository
) : RepoListContract.Interactor {

    override fun loadRepositories(pages: Int) {
        repository.readRepositoryJson(pages, object : RepositotyAllRepositoriesCallback {
            override fun onSuccess(repositoriesList: RepositoriesListResponse) {
                val repositories: ArrayList<Item> =
                    convertGithubRepositoriesListResponseToRepositoriesList(repositoriesList)
                presenter.showCard(repositories)
            }

            override fun onError(errorMessage: String) {
                presenter.errorShowCard(R.string.error_repository)
            }
        })
    }

    override fun convertGithubRepositoriesListResponseToRepositoriesList(listResponse: RepositoriesListResponse): ArrayList<Item> {
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
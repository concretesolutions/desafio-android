package com.bassul.mel.app.interactor

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.repositoriesList.RepoPresenterImpl

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
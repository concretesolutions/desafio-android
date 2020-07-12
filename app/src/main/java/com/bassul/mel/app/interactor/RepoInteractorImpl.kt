package com.bassul.mel.app.interactor

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.repositoriesList.view.MainActivity

class RepoInteractorImpl (
    val activity: MainActivity,
    val repository: RepoRepositoryImpl
) : RepositoriesListContract.Interactor {

    override fun loadRepositories(){

        return repository.readRepositoryJson(object : RepositotyAllRepositoriesCallback {
            override fun onSuccess(repositoriesList: RepositoriesListResponse) {
                val repositories : ArrayList<Item> = convertGithubRepositoriesListResponseToRepositoriesList(repositoriesList)
                activity.showCard(repositories)
            }
        })
    }

    private fun convertGithubRepositoriesListResponseToRepositoriesList(listResponse: RepositoriesListResponse) : ArrayList<Item>{
        val items : ArrayList<Item> = arrayListOf()

        listResponse.items.forEach{
            val repository = Item(it.id,
                it.name,
                it.stargazers_count,
                it.forks_count,
                it.description,
                it.pulls_url)
            items.add(repository)
        }
        return items
    }
}
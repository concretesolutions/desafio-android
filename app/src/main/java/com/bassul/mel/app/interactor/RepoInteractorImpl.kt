package com.bassul.mel.app

import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.view.MainActivity

class RepoInteractorImpl (
    val activity: MainActivity,
    val repository: RepoRepositoryImpl) : RepositoriesListContract.Interactor{

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
            val repository = Item(it.name)
            items.add(repository)
        }
        return items
    }
}
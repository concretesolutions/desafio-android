package com.bassul.mel.app.feature.repositoriesList.interactor

import android.util.Log
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.feature.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback

class RepoInteractorImpl (
    val presenter: RepositoriesListContract.Presenter,
    val repository: RepositoriesListContract.Repository
) : RepositoriesListContract.Interactor {

    override fun loadRepositories(pages : Int){

        return repository.readRepositoryJson(pages, object : RepositotyAllRepositoriesCallback {
            override fun onSuccess(repositoriesList: RepositoriesListResponse) {
                Log.i("MELLINA getSelectedItem", "  repositoriesList:  "+repositoriesList)
                val repositories : ArrayList<Item> = convertGithubRepositoriesListResponseToRepositoriesList(repositoriesList)
                presenter.showCard(repositories)
            }

            override fun onError(errorMessage: String) {
                presenter.errorShowCard(R.string.error_repository)
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
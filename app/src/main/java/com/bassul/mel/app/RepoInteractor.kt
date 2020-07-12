package com.bassul.mel.app

class RepoInteractor(
    val activity: MainActivity,
    val repository: RepoRepository) {

    fun loadRepositories(){

        return repository.readRepositoryJson(object : RepositotyAllRepositoriesCallback{
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
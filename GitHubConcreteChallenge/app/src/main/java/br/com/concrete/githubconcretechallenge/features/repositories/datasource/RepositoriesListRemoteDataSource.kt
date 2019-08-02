package br.com.concrete.githubconcretechallenge.features.repositories.datasource

import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoriesListResponse
import br.com.concrete.githubconcretechallenge.features.repositories.service.RepositoriesListRetrofit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class RepositoriesListRemoteDataSource(private val repositoriesListRetrofit : RepositoriesListRetrofit) {

    fun getRepositoriesList(page: Int) : Single<RepositoriesListResponse> {
        return repositoriesListRetrofit.getRepositoriesList(page = page)
            .observeOn(AndroidSchedulers.mainThread())
    }

//    fun getRepositoriesList() : Single<RepositoriesListResponse> {
//        val repositoriesListResponse = RepositoriesListResponse(items = createItems())
//        return Single.just(repositoriesListResponse)
//    }

//    private fun createItems(): List<RepositoryModel> {
//        val items = mutableListOf<RepositoryModel>()
//
//        for (i in 1..10) {
//            val repositoryModel = RepositoryModel("Repo$i", "Description$i", "${i + 10}", "${i + 20}")
//            items.add(repositoryModel)
//        }
//
//        return items
//    }


}
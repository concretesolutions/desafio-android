package com.desafioandroid.getirepos.view.mainrepolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafioandroid.getirepos.data.GetReposRepository
import com.desafioandroid.getirepos.data.SearchResponseListener
import com.desafioandroid.getirepos.data.dto.Repo
import com.desafioandroid.getirepos.data.dto.RepositoryError
import com.desafioandroid.getirepos.data.dto.SearchResponse

class RepoViewModel(private val repository: GetReposRepository): ViewModel() {
    val repos = MutableLiveData<List<Repo>>(null)
    val error = MutableLiveData<String?>(null)

    fun getRepos(page: Int) {
        repos.value = null
        error.value = null
        repository.getRepos(page, object: SearchResponseListener {
            override fun onSearchResponse(response: SearchResponse) {
                repos.value = response.items
            }

            override fun onError(repositoryError: RepositoryError) {
                error.value = repositoryError.message
            }

        })
    }
}
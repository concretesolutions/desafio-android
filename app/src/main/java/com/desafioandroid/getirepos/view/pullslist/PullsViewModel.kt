package com.desafioandroid.getirepos.view.pullslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafioandroid.getirepos.data.GetReposRepository
import com.desafioandroid.getirepos.data.PullsResponseListener
import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.data.dto.RepositoryError

class PullsViewModel(private val repository: GetReposRepository): ViewModel() {
    val pulls = MutableLiveData<List<PullsResponse>>(null)
    val error = MutableLiveData<String?>(null)

    fun getPulls(owner: String, pullRepository: String) {
        pulls.value = null
        error.value = null

        repository.getPulls(owner, pullRepository, object: PullsResponseListener {
            override fun onPullsResponse(response: List<PullsResponse>) {
                pulls.value = response
            }

            override fun onError(repositoryError: RepositoryError) {
                error.value = repositoryError.message
            }

        })
    }
}
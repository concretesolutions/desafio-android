package com.ccortez.desafioconcreteapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.service.repository.GitHubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    gitHubRepository: GitHubRepository,
    application: Application
) : AndroidViewModel(application) {

    lateinit var observableCar: LiveData<List<PullRequest>>
    lateinit var repositoryID: MutableLiveData<String>
    @JvmField
    var car = ObservableField<Repositories>()

    fun repositoryID(repositoryID: String?) {
        this.repositoryID.value = repositoryID
    }

    companion object {
        private val TAG = RepositoryViewModel::class.java.name
        private val ABSENT: MutableLiveData<*> = MutableLiveData<Any?>()
    }

    init {
        ABSENT.value = null
    }

    init {
        viewModelScope.launch {
            repositoryID = MutableLiveData()
            observableCar = Transformations.switchMap<String, List<PullRequest>>(
                repositoryID
            ) { input: String ->
                if (input.isEmpty()) {
                    Log.i(
                        TAG,
                        "RepositoryViewModel carID is absent!!!"
                    )
//                return@switchMap ABSENT
                }
                Log.i(
                    TAG,
                    "RepositoryViewModel carID is " + repositoryID.value
                )
                getRepositoryPulls(gitHubRepository)
            }
        }
    }

    fun getRepositoryPulls(gitHubRepository: GitHubRepository) : LiveData<List<PullRequest>?> {
        return gitHubRepository.getPulls(repositoryID.value)
    }
}
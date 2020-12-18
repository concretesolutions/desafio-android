package com.ccortez.desafioconcreteapplication.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.service.repository.GitHubRepository
import kotlinx.coroutines.launch

class RepositoryListViewModel @Inject constructor(
    gitHubRepository: GitHubRepository,
    application: Application
) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Repository query so the UI can observe it.
     */
    lateinit var repositoriesObservable: LiveData<Repositories>
    var carRepository = GitHubRepository()

    init {
        this.carRepository = gitHubRepository

        println("application: "+application)
        println("application.applicationContext: "+application.applicationContext)
        println("carRepository: "+gitHubRepository)

        // If any transformation is needed, this can be simply done by Transformations class ...
        viewModelScope.launch {
            repositoriesObservable = gitHubRepository.repositories()
        }
    }

    companion object {
        const val TAG = "RepositoryListViewModel"
    }
}
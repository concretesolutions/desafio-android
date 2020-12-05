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
    carRepository: GitHubRepository,
    application: Application
) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Cars query so the UI can observe it.
     */
    lateinit var repositoriesObservable: LiveData<Repositories>
    lateinit var carDbListObservable: LiveData<List<Repositories>>
    var carRepository = GitHubRepository()

    fun getCarDbListObservable(mContext: Context?): LiveData<List<Repositories>> {
        println("getCarDbListObservable: "+ carDbListObservable)
        return carDbListObservable
    }

    init {
        this.carRepository = carRepository

        println("application: "+application)
        println("application.applicationContext: "+application.applicationContext)
        println("carRepository: "+carRepository)

        // If any transformation is needed, this can be simply done by Transformations class ...
        viewModelScope.launch {
            repositoriesObservable = carRepository.repositories()
        }
    }

    companion object {
        const val TAG = "RepositoryListViewModel"
    }
}
package com.rafael.fernandes.desafioconcrete.ui.activities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.presentation.resources.ResourceState
import com.rafael.fernandes.desafioconcrete.ui.base.BaseViewModel
import com.rafael.fernandes.desafioconcrete.ui.custom.DefaultObserver
import com.rafael.fernandes.desafioconcrete.ui.custom.DefaultSingleObserver
import com.rafael.fernandes.desafioconcrete.ui.custom.SingleLiveEvent
import com.rafael.fernandes.domain.interector.GetGitRepositories
import com.rafael.fernandes.domain.interector.GetRepositories
import com.rafael.fernandes.domain.interector.SaveRepository
import com.rafael.fernandes.domain.model.GitRepositories
import com.rafael.fernandes.domain.model.GitRepositoryRequest
import com.rafael.fernandes.domain.model.Item
import com.rafael.fernandes.domain.repositories.IOFFLineRepository
import javax.inject.Inject

open class RepositoriesViewModel @Inject constructor(
    private val getGitRepositories: GetGitRepositories,
    private val saveRepository: SaveRepository,
    private val getRepositories: GetRepositories
) : BaseViewModel() {
    private var pageNumber: Int = 0
    val mRepositoryGitObservable = MutableLiveData<Resource<GitRepositories>>()
    val mRepositoryListObservable = MutableLiveData<Resource<MutableList<Item>>>()
    val mRefreshObservable = MutableLiveData<Boolean>()
    val mRepositoryObservable = SingleLiveEvent<Long>()

    fun getRepositoriesGit(refresh: Boolean = false) {
        if (refresh) {
            pageNumber = 0
        }
        mRefreshObservable.postValue(refresh)

        pageNumber++
        var gitRepositoryRequest = GitRepositoryRequest(pageNumber)

        getGitRepositories.executeSingle(
            object : DefaultSingleObserver<GitRepositories>(mRepositoryGitObservable) {},
            gitRepositoryRequest
        )
    }

    fun getRepositories(refresh: Boolean = false) {

        mRefreshObservable.postValue(refresh)
        getRepositories.execute(
            object : DefaultObserver<MutableList<Item>>() {
                override fun onNext(it: MutableList<Item>) {
                    Log.i("response", it.size.toString())
                    it.forEach {
                        it.favorite = true
                    }
                    val result = Resource(ResourceState.SUCCESS, it, "")
                    mRepositoryListObservable.postValue(result)
                }

                override fun onError(exception: Throwable) {
                    val result = Resource(ResourceState.ERROR, null, exception.message)
                    mRepositoryListObservable.postValue(result)
                }
            }, null
        )
    }

    fun saveRepository(item: Item) {
        saveRepository.execute(object : DefaultObserver<Long>() {
            override fun onNext(t: Long) {
                mRepositoryObservable.value = t
            }

        }, item)
    }

    override fun myOnCleared() {
    }
}
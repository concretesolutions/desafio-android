package br.com.repository.viewmodel

import android.arch.lifecycle.*
import android.util.Log
import br.com.repository.model.Repository
import br.com.repository.service.request.RepositoryRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel : ViewModel() {
    val TAG = RepositoryViewModel::class.java.name

    val repo = RepositoryRequest()

    private var lRepo = mutableListOf<Repository>()
    private var lRepository = MutableLiveData<List<Repository>>()

    private var showProgress = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var page: Int = 1

    fun requestRepository() {
        if (lRepo.size <= 0) {
            callRepository()
        } else {
            lRepository.value = lRepo
        }
    }

    fun callRepository() {
        repo.getRepository(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repository ->
                    lRepo.add(repository)
                }, { e ->
                    Log.i(TAG, e.message)
                    lRepository.value = lRepo
                    showProgress.value = true
                }, {
                    lRepository.value = lRepo
                    showProgress.value = true
                    page++
                })
    }

    fun getRepo(): LiveData<List<Repository>> = lRepository
    fun showProgress(): LiveData<Boolean> = showProgress

}
package com.example.lucasdias.mvvmpoc.presentation.ui.repositories

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.example.lucasdias.mvvmpoc.domain.useCase.RepositoryUseCase

class RepositoryViewModel(application: Application, private val useCase: RepositoryUseCase): AndroidViewModel(application){

    var repositoryList = useCase.getRepositoryList()
    var requestPermissionStatus = useCase.getRequestPermissionStatus()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadRepositoryPageFromApi(page: Int) {
        useCase.loadRepositoryPageFromApi(page)
    }
}
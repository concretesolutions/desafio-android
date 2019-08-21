package com.concrete.desafio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.desafio.data.GestorDePullRequest
import com.concrete.desafio.data.PullRequest

class PullRequestViewModel(val gestorPullrequest: GestorDePullRequest): ViewModel() {

    private var mPullRequest: MutableLiveData<MutableList<PullRequest>>? = null

    fun getPullRequest(): LiveData<MutableList<PullRequest>>? {
        if(mPullRequest == null){
            mPullRequest = gestorPullrequest.getPullRequest()
        }

        return mPullRequest
    }

    fun buscarPullRequest(repositorio: String, autor: String){
        gestorPullrequest.buscarPullRequest(repositorio, autor)
    }

}
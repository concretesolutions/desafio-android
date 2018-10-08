package com.joaoibarra.gitapp.features.pull

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.joaoibarra.gitapp.features.repository.PullViewModel

class PullViewModelFactory (private val user: String, private val repo: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  PullViewModel(user, repo) as T
    }

}
package com.rafaelmfer.consultinggithub.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rafaelmfer.consultinggithub.mvvm.model.GitHubRepository
import com.rafaelmfer.consultinggithub.mvvm.viewmodel.GitHubRepositoriesViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(GitHubRepositoriesViewModel::class.java) ->
                    GitHubRepositoriesViewModel(GitHubRepository())

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
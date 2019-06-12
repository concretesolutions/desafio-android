package com.abs.javarepos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abs.javarepos.model.Repo
import com.abs.javarepos.model.repository.RepoRepository

class ReposViewModel : ViewModel() {

    var repos: LiveData<ArrayList<Repo>> = RepoRepository().getRepos()
}
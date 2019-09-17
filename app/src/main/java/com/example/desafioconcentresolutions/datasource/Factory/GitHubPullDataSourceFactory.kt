package com.example.desafioconcentresolutions.datasource.Factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.desafioconcentresolutions.datasource.GitHubPullDataSource
import com.example.desafioconcentresolutions.models.GitHubPull

class GitHubPullDataSourceFactory(private val ownerName:String, private val login:String) : DataSource.Factory<Int, GitHubPull>(){
    private val gitHubPullDataSourceLive = MutableLiveData<GitHubPullDataSource>()

    fun getGitHubPullDataSourceLive() = gitHubPullDataSourceLive

    override fun create(): DataSource<Int, GitHubPull> {
        val gitHubPullDataSource = GitHubPullDataSource(ownerName, login)
        gitHubPullDataSourceLive.postValue(gitHubPullDataSource)
        return gitHubPullDataSource
    }
}
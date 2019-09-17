package com.example.desafioconcentresolutions.datasource.Factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.desafioconcentresolutions.datasource.GitHubRepoDataSource
import com.example.desafioconcentresolutions.models.GitHubRepo

class GitHubRepoDataSourceFactory : DataSource.Factory<Int, GitHubRepo>(){

    private val gitHubRepoDataSourceLive = MutableLiveData<GitHubRepoDataSource>()

    fun getGitHubRepoDataSourceLive(): LiveData<GitHubRepoDataSource> = gitHubRepoDataSourceLive

    override fun create(): DataSource<Int, GitHubRepo> {
        val gitHubRepoDataSource = GitHubRepoDataSource()
        gitHubRepoDataSourceLive.postValue(gitHubRepoDataSource)
        return gitHubRepoDataSource
    }

}
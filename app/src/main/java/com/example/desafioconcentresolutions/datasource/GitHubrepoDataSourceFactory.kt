package com.example.desafioconcentresolutions.datasource

import androidx.paging.DataSource
import com.example.desafioconcentresolutions.models.GitHubRepo

class GitHubrepoDataSourceFactory : DataSource.Factory<Int,GitHubRepo>(){
    override fun create(): DataSource<Int, GitHubRepo> {
        return GitHubRepoDataSource()
    }

}
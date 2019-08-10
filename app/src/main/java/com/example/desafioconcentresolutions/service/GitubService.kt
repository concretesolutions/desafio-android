package com.example.desafioconcentresolutions.service

import com.example.desafioconcentresolutions.models.GitHubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubService : IGitHubService {
    override fun listAllRepoByPage(page: Int): List<GitHubRepo> {
        val callBack = GitHubAPI.getGitHubApi().listAllRepoByPage(page).execute()
        return if(callBack.isSuccessful) callBack.body()?.items ?: emptyList() else emptyList<GitHubRepo>()
    }
}
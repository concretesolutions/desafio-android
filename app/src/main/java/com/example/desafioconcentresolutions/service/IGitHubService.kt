package com.example.desafioconcentresolutions.service

import com.example.desafioconcentresolutions.models.GitHubRepo

interface IGitHubService {
    fun listAllRepoByPage(page:Int):List<GitHubRepo>
}
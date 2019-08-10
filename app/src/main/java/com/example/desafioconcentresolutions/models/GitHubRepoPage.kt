package com.example.desafioconcentresolutions.models

data class GitHubRepoPage(
    val total_count:Int,
    val incomplete_results:Boolean,
    val items: List<GitHubRepo>
)
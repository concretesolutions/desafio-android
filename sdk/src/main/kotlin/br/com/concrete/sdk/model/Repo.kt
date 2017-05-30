package br.com.concrete.sdk.model

import com.google.gson.annotations.Expose

data class Repo(
        @Expose val id: Long,
        @Expose val name: String,
        @Expose val fullName: String,
        @Expose val description: String,
        @Expose val forks: Long,
        @Expose val stargazersCount: Long,
        @Expose val owner: User
)
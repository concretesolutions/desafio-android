package com.accenture.githubrepositories.pojo

data class Repository (

     var id: Int? = 0,
     var name: String = "",
     var description: String = "",
     var stargazers_count: Int = 0,
     var forks_count: Int = 0,
     var owner: RepositoryUser? = null
)

package com.example.artul.models

data class Repository(var id: Long,
                      var name: String,
                      var description: String,
                      var login: String,
                      var stargazers_count: Long,
                      var forks_count: Int,
                      var owner: Author) {
}
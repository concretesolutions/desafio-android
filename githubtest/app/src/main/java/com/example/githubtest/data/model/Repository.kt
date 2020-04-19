package com.example.githubtest.data.model


data class Repository(var id: Int,
                      var name: String,
                      var full_name: String,
                      var description: String,
                      var owner: User,
                      var stargazers_count: Int,
                      var forks_count: Int)

package com.hako.githubapi.domain.requests

data class QueryRepository(
    val language: String = "language:Java",
    val sort: String = "stars",
    val page: Int = 1
)

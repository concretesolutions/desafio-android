package br.com.concrete.desafio.data.model.dto.enum

enum class SortPullRequest(val value: String) {
    CREATED("created"),
    UPDATED("updated"),
    POPULARITY("popularity"),
    LONG_RUNNING("long-running")
}
package com.concretesolutions.diegosouza.concretesolutions.Model

data class Lista(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ItemsLista>
)

data class ItemsLista(

    val name: String,
    val full_name: String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int
)

data class Owner(

    val login: String,
    val avatar_url: String
)
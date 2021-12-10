package com.example.desafio_android.data.model

data class repositoriesInfo (
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)
package com.jmc.desafioandroidkotlin.data.model.api

data class SearchResultEntry<T>(
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<T>
)
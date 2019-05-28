package com.jmc.desafioandroidkotlin.domain.model

data class SearchResultModel<T>(
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<T>
)
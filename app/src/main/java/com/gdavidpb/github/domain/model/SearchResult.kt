package com.gdavidpb.github.domain.model

data class SearchResult<T>(
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<T>
)
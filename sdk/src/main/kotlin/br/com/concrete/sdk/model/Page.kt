package br.com.concrete.sdk.model

import com.google.gson.annotations.Expose

data class Page<out T>(
        @Expose val totalCount: Long,
        @Expose val incompleteResults: Boolean,
        @Expose val items: List<T> = emptyList(),
        var nextPage: Int? = null
)
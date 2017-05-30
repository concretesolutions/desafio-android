package br.com.concrete.sdk.model

import com.google.gson.annotations.Expose

data class Page<T>(
        @Expose val totalCount: Long,
        @Expose val incompleteResults: Boolean,
        @Expose val items: List<T> = emptyList()
)


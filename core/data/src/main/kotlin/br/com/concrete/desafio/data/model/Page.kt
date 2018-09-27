package br.com.concrete.desafio.data.model

import com.google.gson.annotations.Expose

data class Page<T>(
        @Expose val totalCount: Long,
        @Expose val incompleteResults: Boolean,
        @Expose val items: ArrayList<T> = ArrayList(),
        var nextPage: Int? = null
)
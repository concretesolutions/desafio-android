package com.pedrenrique.githubapp.core.data

data class PaginatedData<T>(val page: Int, val content: List<T>)
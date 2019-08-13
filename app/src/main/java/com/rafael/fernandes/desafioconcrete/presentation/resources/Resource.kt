package com.rafael.fernandes.desafioconcrete.presentation.resources

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)
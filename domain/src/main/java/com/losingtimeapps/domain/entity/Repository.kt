package com.losingtimeapps.domain.entity

data class Repository(
    val id: Long,
    val name: String,
    val description: String = "",
    val starsAmount: Long,
    val forksAmount: Long,
    val author: Author
)

package dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data

import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.response.Repository

data class Repository(
    val id: Int,
    val name: String,
    val description: String,
    val owner: Owner,
    private val starsCount: Int,
    private val forksCount: Int
) {
    constructor(repository: Repository) : this(
        id = repository.id,
        name = repository.name,
        description = repository.description.orEmpty(),
        owner = Owner(repository.owner.avatarUrl, repository.owner.userName),
        starsCount = repository.starsCount,
        forksCount = repository.forksCount
    )

    fun starsCount() = starsCount.toString()

    fun forksCount() = forksCount.toString()
}
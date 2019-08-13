package com.rafael.fernandes.desafioconcrete.util

import com.rafael.fernandes.domain.model.GitRepositories
import com.rafael.fernandes.domain.model.Item
import com.rafael.fernandes.domain.model.Owner
import com.rafael.fernandes.domain.model.User

object TestUtil {

    fun createUser(login: String) = User(
        login = login,
        avatarUrl = null,
        bio = null

    )

    fun createRepos(count: Int, owner: String, name: String, description: String): List<Item> {
        return (0 until count).map {
            createRepo(
                owner = owner + it,
                name = name + it,
                description = description + it
            )
        }
    }

    fun createRepo(owner: String, name: String, description: String) = createRepo(
        id = 22790488,
        owner = owner,
        name = name,
        description = description
    )

    fun createRepo(id: Int, owner: String, name: String, description: String) = Item(
        id = id,
        name = name,
        fullName = "$owner/$name",
        description = description,
        owner = Owner(owner, null),
        stargazersCount = 3,
        favorite = false
    )
}
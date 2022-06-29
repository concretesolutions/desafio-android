package com.desafioandroid.getirepos.view.mainrepolist

import com.desafioandroid.getirepos.data.dto.Owner

class RepoItem(
    val id: Long,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val description: String?,
    val pullsUrl: String,
    val stargazersCount: Long,
    val forksCount: Long,
    val owner: Owner
) {

}
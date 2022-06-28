package com.desafioandroid.getirepos.view.mainrepolist

import com.desafioandroid.getirepos.data.dto.Owner

data class Repo(val id: Long,
           val nodeId: String,
           val name: String,
           val fullName: String,
           val pullsUrl: String,
                val stargazersCount: Long,
                val forksCount: Long,
           val owner: Owner)
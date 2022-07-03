package com.desafioandroid.getirepos.data.utils

import com.desafioandroid.getirepos.data.dto.Repo
import com.desafioandroid.getirepos.view.mainrepolist.RepoItem

object RepoTranslator {
    fun translateRepoToRepoItem(repoList: List<Repo>): ArrayList<RepoItem> {
        val repoItemList = ArrayList<RepoItem>()
        repoList.forEach {
            repoItemList.add(
                RepoItem(it.id,
                    it.nodeId,
                    it.name,
                    it.fullName,
                    it.description,
                    it.pullsUrl,
                    it.stargazersCount,
                    it.forksCount,
                    it.owner)
            )
        }
        return repoItemList
    }
}
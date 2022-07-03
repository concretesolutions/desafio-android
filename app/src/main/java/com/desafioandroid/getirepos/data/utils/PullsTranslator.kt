package com.desafioandroid.getirepos.data.utils

import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.view.pullslist.PullsItem

object PullsTranslator {
    fun translatePullsToPullsItem(pullsList: List<PullsResponse>): ArrayList<PullsItem> {
        val pullsItemList = ArrayList<PullsItem>()
        pullsList.forEach {
            pullsItemList.add(
                PullsItem(it.state,
                    it.title,
                    it.body,
                    it.user,
                    it.htmlUrl)
            )
        }
        return pullsItemList
    }
}
package com.bassul.mel.app.feature.repositoriesList.view.adapter

import com.bassul.mel.app.domain.Item

interface AdapterRepoContract {
    fun addItems(newItems: List<Item>)
}
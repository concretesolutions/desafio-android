package com.bassul.mel.app.feature.repositoriesList.view.adapter

import com.bassul.mel.app.domain.Item

interface AdapterItemsContract {

    fun addItems(list: List<Item>)
}
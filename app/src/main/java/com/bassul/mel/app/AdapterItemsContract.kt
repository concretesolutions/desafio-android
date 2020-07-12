package com.bassul.mel.app

import com.bassul.mel.app.domain.Item

interface AdapterItemsContract {

    fun addItems(list: List<Item>)
}
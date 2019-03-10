package com.hako.githubapi.util

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.hako.githubapi.domain.entities.Repository


class RepoDiffUtilCallback(
    private var newList: List<Repository>,
    private var oldList: MutableLiveData<List<Repository>>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.value?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList.count()
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        oldList.value?.let {
            return newList[newItemPosition].id == it[oldItemPosition].id
        }
        return false
    }
}
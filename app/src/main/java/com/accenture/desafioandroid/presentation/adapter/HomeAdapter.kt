package com.accenture.desafioandroid.presentation.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import android.view.LayoutInflater
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.mvvm.model.Item
import com.accenture.desafioandroid.presentation.adapter.viewholder.HomeViewHolder
import com.accenture.desafioandroid.presentation.listerner.HomeListener


class HomeAdapter(val context: Context, diffCallback: DiffUtil.ItemCallback<Item>, private val homeListener: HomeListener) :
    PagedListAdapter<Item, HomeViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return HomeViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {

        val itemRepositorie = getItem(position)
        if (itemRepositorie != null)
            viewHolder.setDataWallet(itemRepositorie)

        viewHolder.content.setOnClickListener {
            val owner = itemRepositorie!!.owner!!.login
            val repo = itemRepositorie.name
            homeListener.goDetails(owner!!, repo!!)
        }


    }

}

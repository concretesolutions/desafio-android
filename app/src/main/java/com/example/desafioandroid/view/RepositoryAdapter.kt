package com.example.desafioandroid.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.CardRepositoryBinding
import com.example.desafioandroid.schemas.RepositoryItem
import com.example.desafioandroid.viewModel.ItemRepositoryViewModel

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder>() {
    private var repositoryList: List<RepositoryItem>? = null

    override fun getItemCount(): Int {
        if (repositoryList == null)
            return 0
        return repositoryList!!.size
    }

    internal fun setRepositoryList(repositoryList: List<RepositoryItem>) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.RepositoryAdapterViewHolder, position: Int) {
        holder.bindPeople(repositoryList!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapterViewHolder {
        val itemPeopleBinding: CardRepositoryBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.card_repository,
            parent, false)

        return RepositoryAdapterViewHolder(itemPeopleBinding)
    }

    class RepositoryAdapterViewHolder(var mItemRepositoryBinding: CardRepositoryBinding) :
        RecyclerView.ViewHolder(mItemRepositoryBinding.cardRepository) {

        fun bindPeople(repositoryItem: RepositoryItem) {
            if (mItemRepositoryBinding.repositoryViewModel == null) {
                mItemRepositoryBinding.repositoryViewModel = ItemRepositoryViewModel(repositoryItem, itemView.context)

            } else {
                mItemRepositoryBinding.repositoryViewModel!!.mRepositoryItem = repositoryItem
            }
        }
    }
}

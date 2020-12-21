package com.ccortez.desafioconcrete.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ccortez.desafioconcrete.R
import com.ccortez.desafioconcrete.databinding.ItemRepositoryBinding
import com.ccortez.desafioconcrete.model.ItemEntity
import com.ccortez.desafioconcrete.ui.callback.RepositoryClickCallback

class RepositoryPagedListAdapter(private val repositoryClickCallback: RepositoryClickCallback?) : PagedListAdapter<ItemEntity, RepositoryPagedListAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    inner class ViewHolder(val binding: ItemRepositoryBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: ItemEntity) {

//            binding.item = item.items?.get(adapterPosition)
            binding.item = item

//            binding.repoName.text = repository.
//            binding.repoDesc.text = repository.description
//            binding.tvEmail.text = userEntity.email
//
            binding.repoProfileImage.load(
                item.owner?.avatar_url) {
                placeholder(R.drawable.ic_if_git_branch)
                error(R.drawable.ic_broken_image)
            }

            binding.callback = repositoryClickCallback
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemEntity>() {
            override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity) =
                oldItem == newItem

        }

        private val TAG = RepositoryPagedListAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        getItem(position)?.let {
//            it.items?.get(0)?.let { it1 -> holder.bindView(it1) }
//        }
        getItem(position)?.let { holder.bindView(it) }
    }

}
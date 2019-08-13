package com.rafael.fernandes.desafioconcrete.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafael.fernandes.desafioconcrete.R
import com.rafael.fernandes.desafioconcrete.databinding.ListRepositoryItemBinding
import com.rafael.fernandes.domain.model.Item

class ListRepositoryAdapter constructor(private val itemClick: (Item) -> Unit) :
    ListAdapter<Item, ListRepositoryViewHolder>(ScheduleDiffCallback()) {
    var imageViewItemClick: ((Item) -> Unit)? = null
    private var listItem: ArrayList<Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepositoryViewHolder =
        ListRepositoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_repository_item, parent, false)
        )

    override fun onBindViewHolder(holder: ListRepositoryViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position);
        binding?.repository = item
        binding?.imageViewFavorite?.setOnClickListener {
            imageViewItemClick?.invoke(item)
        }
        binding?.executePendingBindings()
        binding?.root?.setOnClickListener {
            itemClick.invoke(item)
        }
    }

    fun updateList(list: List<Item>?) {
        if (list != null) {
            listItem.addAll(list)
        }

        notifyDataSetChanged()
    }

    fun updateItems(list: List<Item>?) {
        var position = 0
        list?.forEach { favoriteItem ->
            run {
                listItem.forEach {
                    if (favoriteItem.id == it.id) {
                        it.favorite = true
                    }
                    position++
                }
            }
        }
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<Item> {
        return listItem
    }

    override fun submitList(list: MutableList<Item>?) {
        if (list != null) {
            listItem.addAll(list)
        }
        super.submitList(listItem)
    }

    fun resetDataList() {
        getList().clear()
        notifyDataSetChanged()
    }

    private class ScheduleDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
    }
}

class ListRepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ListRepositoryItemBinding? = DataBindingUtil.bind(view)
}
package com.example.desafioandroidapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.data.dto.Item
import com.example.desafioandroidapp.databinding.RepositoryItemBinding
import com.squareup.picasso.Picasso

class RepositoryMainAdapter(val listener: ItemsListener): RecyclerView.Adapter<RepositoryMainAdapter.ItemViewHolder>() {

    var items : ArrayList<Item> = ArrayList()
    interface ItemsListener{
        fun selectedItem(
            ownerName: String,
            repository: String)
    }
    fun setRepositories(newItem: List<Item>) {
        if(this.items.isNullOrEmpty()){
            this.items = newItem as ArrayList<Item>
        }else {
            this.items.addAll(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(layoutInflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(this.items[position])
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    class ItemViewHolder(
        private val binding: RepositoryItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            this.binding.repoName.text = item.name
            this.binding.description.text = item.description
            this.binding.forkNumber.text = item.forks_count
            this.binding.starNumber.text = item.stargazers_count
            this.binding.ownerName.text = item.owner.login
            Picasso.get().load(item.owner.avatar_url).into(this.binding.imageView)
        }
    }
}
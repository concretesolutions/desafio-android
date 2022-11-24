package com.example.desafioandroidapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.data.dto.RepositoryItem
import com.example.desafioandroidapp.databinding.RepositoryItemBinding
import com.squareup.picasso.Picasso

class RepositoryMainAdapter(private val listener: ItemsListener): RecyclerView.Adapter<RepositoryMainAdapter.ItemViewHolder>() {

    var repositoryItems : ArrayList<RepositoryItem> = ArrayList()
    interface ItemsListener{
        fun selectedItem(
            repositoryItem : RepositoryItem)
    }
    fun setRepositories(newRepositoryItem: List<RepositoryItem>) {
        if(this.repositoryItems.isEmpty()){
            this.repositoryItems = newRepositoryItem as ArrayList<RepositoryItem>
        }else {
            this.repositoryItems.addAll(newRepositoryItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(layoutInflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(this.repositoryItems[position], listener)
    }

    override fun getItemCount(): Int {
        return this.repositoryItems.size
    }

    class ItemViewHolder(
        private val binding: RepositoryItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(repositoryItem: RepositoryItem, listener: ItemsListener) {
            this.binding.repoName.text = repositoryItem.name
            this.binding.description.text = repositoryItem.description
            this.binding.forkNumber.text = repositoryItem.forks_count
            this.binding.starNumber.text = repositoryItem.stargazers_count
            this.binding.ownerName.text = repositoryItem.owner.login
            Picasso.get().load(repositoryItem.owner.avatar_url).into(this.binding.imageView)
            this.binding.cardView.setOnClickListener{
                listener.selectedItem(repositoryItem)
            }
        }


    }
}
package com.example.desafioandroidapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.data.dto.Item
import com.example.desafioandroidapp.databinding.RepositoryItemBinding
import com.squareup.picasso.Picasso

class MainActivityAdapter: RecyclerView.Adapter<MainActivityAdapter.ItemViewHolder>()  {

    private lateinit var items : List<Item>
    fun setRepositories(newItem: List<Item>) {
        this.items = newItem
        this.notifyDataSetChanged()
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
            this.binding.name.text = item.name
            this.binding.description.text = item.description
            Picasso.get().load(item.owner.avatar_url).into(this.binding.imageView)
        }
    }
}
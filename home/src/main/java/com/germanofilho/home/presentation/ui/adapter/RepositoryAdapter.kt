package com.germanofilho.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.germanofilho.home.R
import com.germanofilho.network.feature.repositorylist.model.entity.Item
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val onItemClickListener: ((String) -> Unit)) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItem(item: List<Item>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View, private val onItemClickListener: ((String) -> Unit)) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            with(view) {
                tv_repository_title.text = item.name
                tv_repository_description.text = item.description
                tv_repository_name_and_last_name.text = item.fullName
                tv_repository_username.text = item.owner?.login
                tv_repository_fork.text = item.forksCount.toString()
                tv_repository_stars.text = item.stargazersCount.toString()

                Glide
                    .with(view.context)
                    .load(item.owner?.avatarUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .circleCrop()
                    .into(iv_avatar)

                this.setOnClickListener {
                    onItemClickListener.invoke(item.fullName ?: "")
                }
            }
        }
    }
}
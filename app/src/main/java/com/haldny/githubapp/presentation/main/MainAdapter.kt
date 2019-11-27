package com.haldny.githubapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.haldny.githubapp.R
import com.haldny.githubapp.common.extension.dateFormat
import com.haldny.githubapp.common.extension.decimalFormat
import com.haldny.githubapp.domain.model.Repository
import kotlinx.android.synthetic.main.repository.view.*

class MainAdapter(private val onItemClickListener: ((Repository) -> Unit)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_LIST = 0
        const val ITEM_BOTTOM = 1
    }

    private var repositories = ArrayList<Repository>()
    private var isLoadingAdded = false

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.repository, viewGroup, false),
            onItemClickListener)
    }

    override fun getItemCount(): Int {
        return if (isLoadingAdded) {
            repositories.size + 1
        } else {
            repositories.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < repositories.size) ITEM_LIST else ITEM_BOTTOM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                if (position < repositories.size) {
                    val repository = repositories[position]
                    holder.bindView(repository)
                }
            }
        }
    }

    fun addList(repositories: List<Repository>) {
        val oldSize = this.repositories.size
        this.repositories = repositories as ArrayList<Repository>

        notifyItemRangeInserted(oldSize, this.repositories.size)
        addItemBottom()
    }

    fun clearList() {
        isLoadingAdded = false
        this.repositories.clear()
        notifyDataSetChanged()
    }

    fun addItemBottom() {
        isLoadingAdded = true
    }

    fun removeItemBottom() {
        isLoadingAdded = false
        notifyItemRangeRemoved(this.repositories.size, 1)
    }

    class RepositoryViewHolder(private val view: View,
                               private val onItemClickListener: ((Repository) -> Unit)) :
        RecyclerView.ViewHolder(view) {

        private val name: TextView = view.tv_repository_name
        private val description: TextView = view.tv_repository_description
        private val forks: TextView = view.tv_repository_fork
        private val stars: TextView = view.tv_repository_star
        private val owner: TextView = view.tv_repository_owner
        private val date: TextView = view.tv_repository_date
        private val avatar: ImageView = view.iv_avatar

        fun bindView(repository: Repository) = with(view) {
            name.text = repository.name
            description.text = repository.description
            forks.text = repository.forks.decimalFormat()
            stars.text = repository.stars.decimalFormat()
            owner.text = repository.owner.login
            date.text = repository.createdAt.dateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            val drawableImageDefault = R.drawable.github

            Glide.with(context)
                .load(repository.owner.avatarUrl)
                .placeholder(drawableImageDefault)
                .error(drawableImageDefault)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar)

            this.setOnClickListener {
                onItemClickListener(repository)
            }
        }
    }
}
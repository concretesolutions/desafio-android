package com.example.desafioandroid.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.model.SearchModel
import com.example.desafioandroid.databinding.FragmentRepoBinding
import javax.inject.Inject


class RepoAdapter @Inject constructor() : ListAdapter<RepoModel, RepoAdapter.RepoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RepoModel>() {
        override fun areItemsTheSame(
            oldItem: RepoModel,
            newItem: RepoModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: RepoModel,
            newItem: RepoModel
        ): Boolean {
            return oldItem.idRepo === newItem.idRepo
        }
    }

    private var onItemClickListener: ((RepoModel) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (RepoModel) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = FragmentRepoBinding.inflate(LayoutInflater.from(parent.context))
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(repoViewHolder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        repoViewHolder.bind(repo)
    }

    inner class RepoViewHolder(private val binding: FragmentRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoModel) {
            binding.ivAvatarUser.load(repo.owner_repos.avatarUrl)
            binding.tvRepoName.text = repo.nameRepo
            binding.tvStar.text = repo.stars.toString()
            binding.tvForks.text = repo.forks.toString()
            binding.tvRepoDescription.text = repo.descriptionRepo
            binding.tvOwnerName.text = repo.owner_repos.loginOwner
            binding.layoutRepo.setOnClickListener {
                onItemClickListener?.invoke(repo)
            }
        }
    }
}

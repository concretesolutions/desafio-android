package com.example.desafioandroid.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafioandroid.data.model.RepositoriesModel
import com.example.desafioandroid.databinding.FragmentRepoBinding
import com.example.desafioandroid.domain.GetRepoByOwner
import javax.inject.Inject


class RepoAdapter @Inject constructor() : ListAdapter<RepositoriesModel, RepoAdapter.RepoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RepositoriesModel>() {
        override fun areItemsTheSame(
            oldItem: RepositoriesModel,
            newItem: RepositoriesModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: RepositoriesModel,
            newItem: RepositoriesModel
        ): Boolean {
            return oldItem.id_repos === newItem.id_repos
        }
    }

    private var onItemClickListener: ((RepositoriesModel) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (RepositoriesModel) -> Unit) {
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

        fun bind(repo: RepositoriesModel) {
            binding.ivAvatarUser.load(repo.owner_repos.avatar_url_owner)
            binding.tvRepoName.text = repo.name_repos
            binding.tvRepoDescription.text = repo.description_repos
            binding.tvOwnerName.text = repo.owner_repos.login_owner
            binding.layoutRepo.setOnClickListener {
                onItemClickListener?.invoke(repo)
            }
        }
    }
}

package com.example.desafioandroid.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.FragmentRepoBinding
import com.example.desafioandroid.domain.model.Repo
import javax.inject.Inject


class RepoAdapter @Inject constructor() :
    ListAdapter<Repo, RepoAdapter.RepoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(
            oldItem: Repo,
            newItem: Repo
        ): Boolean {
            return oldItem === newItem

        }

        override fun areContentsTheSame(
            oldItem: Repo,
            newItem: Repo
        ): Boolean {
            return oldItem.idRepo === newItem.idRepo
        }
    }

    private var onItemClickListener: ((Repo) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (Repo) -> Unit) {
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

        fun bind(repo: Repo) {
            binding.ivAvatarUser.load(repo.owner_repos.avatarUrl) {
                crossfade(true)
                    .placeholder(R.drawable.ic_launcher_background) //EDU agregar placeholder
                    .transformations(CircleCropTransformation())
            }

            binding.ivFork.load(R.drawable.ic_baseline_fork)
            binding.ivStar.load(R.drawable.ic_baseline_star)
            binding.tvRepoName.text = repo.nameRepo
            binding.tvOwnerFullName.text = repo.fullNameRepo
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

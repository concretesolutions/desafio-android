package com.example.desafioandroid.data.model

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafioandroid.databinding.FragmentRepoBinding


class RepoRecyclerViewAdapter(
    private val repo: List<RepositoriesModel>,
    private val listener: RepoSelectionListener
): RecyclerView.Adapter<RepoRecyclerViewAdapter.RepoViewHolder>() {

    interface RepoSelectionListener {
        fun select(activityName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentRepoBinding.inflate(layoutInflater,parent,false)
        return RepoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(this.repo[position])
    }

    override fun getItemCount(): Int {
        return this.repo.size
    }

    class RepoViewHolder( private val binding: FragmentRepoBinding, private val listener: RepoSelectionListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepositoriesModel) {
            binding.ivAvatarUser.load(repo.owner.avatar_url)

            Log.i("RepoViewHolder",repo.name)

            binding.tvRepoName.text = repo.name
            binding.tvRepoDescription.text = repo.description
            this.binding.tvRepoName.setOnClickListener{
                listener.select(binding.tvRepoName.text.toString().lowercase())
            }
        }
    }
}

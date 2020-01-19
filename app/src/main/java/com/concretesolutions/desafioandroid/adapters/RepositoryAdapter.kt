package com.concretesolutions.desafioandroid.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.concretesolutions.desafioandroid.databinding.RepositoryItemBinding
import com.concretesolutions.desafioandroid.model.Repository
import com.concretesolutions.desafioandroid.viewmodel.RepositoryViewModel


class RepositoryAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder>() {

    private var repositories: MutableList<Repository> = arrayListOf()

    fun updateRepositories(repos: List<Repository>) {
        repositories.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int)
            : RepositoryAdapterViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RepositoryItemBinding.inflate(inflater, viewGroup, false)

        return RepositoryAdapterViewHolder(binding, listener)

    }

    override fun getItemCount() = repositories.count()

    override fun onBindViewHolder(holder: RepositoryAdapterViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    class RepositoryAdapterViewHolder(
        private val binding: RepositoryItemBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                binding.repository?.let {
                    listener.onItemClick(it)
                }
            }
        }

        fun bind(repository: Repository) {
            Log.i("teste", repository.toString())
            binding.repository = RepositoryViewModel(repository)
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repositoryViewModel: RepositoryViewModel)
    }
}
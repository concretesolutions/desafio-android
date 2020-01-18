package com.concretesolutions.desafioandroid.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.databinding.RepositoryItemBinding
import com.concretesolutions.desafioandroid.model.Repository
import com.concretesolutions.desafioandroid.viewmodel.AvatarViewModel
import com.concretesolutions.desafioandroid.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.repository_counts.view.*
import kotlinx.android.synthetic.main.title_description.view.*
import kotlinx.android.synthetic.main.user_avatar.view.*
import com.squareup.picasso.Picasso



class RepositoryAdapter(private val repositories: List<Repository>, private val listener: OnItemClickListener)
            : RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int)
            : RepositoryAdapterViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RepositoryItemBinding.inflate(inflater)

        return RepositoryAdapterViewHolder(binding)

    }

    override fun getItemCount() = repositories.count()

    override fun onBindViewHolder(holder: RepositoryAdapterViewHolder, position: Int) {
        holder.bind(repositories[position], listener)
    }

    class RepositoryAdapterViewHolder(private val binding: RepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository, listener: OnItemClickListener) {
            binding.repository = RepositoryViewModel(repository)
            binding.executePendingBindings()
//            setOnClickListener(View.OnClickListener { listener.onItemClick(repository)  })
//            Picasso.with(view.context).load(repository.owner.avatarUrl)
//                .into(view.fotoUser)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repository: Repository)
    }
}
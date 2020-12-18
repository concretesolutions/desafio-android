package com.ccortez.desafioconcreteapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.ItemRepositoryBinding
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryClickCallback
import com.squareup.picasso.Picasso

class RepositoryAdapter(private val repositoryClickCallback: RepositoryClickCallback?) :
    RecyclerView.Adapter<RepositoryAdapter.CarViewHolder>() {

    internal var repositories: Repositories? = null

    fun setRepositories(repositories: Repositories) {
        if (this.repositories == null) {
            this.repositories = repositories
            notifyItemRangeInserted(0, repositories.items!!.size)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarViewHolder {
        val binding: ItemRepositoryBinding =
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_repository,
                    parent, false
                )
        binding.callback = repositoryClickCallback
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int
    ) {
        holder.binding.item = repositories!!.items!![position]

        if (position == repositories!!.items!!.size - 1){
            Log.d(TAG, "item position: " + position)
        }

        Log.d(TAG, "item: " + repositories!!.items!![position].description)
        Picasso.get()
            .load(repositories!!.items!![position].owner?.avatar_url)
            .placeholder(R.drawable.ic_profile_icon)
            .error(R.drawable.ic_alert)
            .into(holder.avatarImage)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (repositories == null) 0 else repositories!!.items!!!!.size
    }

    class CarViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val avatarImage: ImageView

        init {
            avatarImage = binding.repoProfileImage
        }
    }

    companion object {
        private val TAG = RepositoryAdapter::class.java.simpleName
    }

}
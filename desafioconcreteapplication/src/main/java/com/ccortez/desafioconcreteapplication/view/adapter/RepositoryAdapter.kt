package com.ccortez.desafioconcreteapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.ItemRepositoryBinding
import com.ccortez.desafioconcreteapplication.databinding.RepositoryListItemClBinding
import com.ccortez.desafioconcreteapplication.service.model.Items
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryClickCallback
import com.squareup.picasso.Picasso

class RepositoryAdapter(private val repositoryClickCallback: RepositoryClickCallback?) :
    RecyclerView.Adapter<RepositoryAdapter.CarViewHolder>() {

    internal var repositoryList: List<Repositories>? = null
    internal var repositories: Repositories? = null
    internal var repositoryItems: List<Items>? = null

    fun setRepositories(repositories: Repositories) {
        if (this.repositories == null) {
            this.repositories = repositories
            notifyItemRangeInserted(0, repositories.items!!.size)
        }
    }

    fun setItems(repositories: Repositories) {
        if (this.repositoryItems == null) {
            this.repositoryItems = repositories.items
            notifyItemRangeInserted(0, repositories.items!!.size)
        }
    }

    fun setCarList(repositoryList: List<Repositories>) {
        if (this.repositoryList == null) {
            this.repositoryList = repositoryList
            notifyItemRangeInserted(0, repositoryList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@RepositoryAdapter.repositoryList!!.size
                }

                override fun getNewListSize(): Int {
                    return repositoryList.size
                }

                override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
//                    return this@RepositoryAdapter.repositoryList!![oldItemPosition].id ==
//                            repositoryList[newItemPosition].id
                    return false;
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val (id) = repositoryList[newItemPosition]
                    val (id1) = repositoryList[oldItemPosition]
                    return id == id1
                }
            })
            this.repositoryList = repositoryList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarViewHolder {
        val binding: ItemRepositoryBinding =
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),  // R.layout.car_list_item,
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
        // Log.d(TAG, "img: "+carList.get(position).imagem);
        Log.d(TAG, "item: " + repositories!!.items!![position].description)
        Picasso.get()
            .load(repositories!!.items!![position].owner?.avatar_url)
            .placeholder(R.drawable.ic_car)
            .error(R.drawable.ic_alert)
            .into(holder.carImage)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (repositories == null) 0 else repositories!!.items!!!!.size
    }

    class CarViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val carImage: ImageView

        init {
            carImage = binding.repoProfileImage
        }
    }

    companion object {
        private val TAG = RepositoryAdapter::class.java.simpleName
    }

}
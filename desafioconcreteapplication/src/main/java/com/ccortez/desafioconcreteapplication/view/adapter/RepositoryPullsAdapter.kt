package com.ccortez.desafioconcreteapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.PullListItemClBinding
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryPullsItemClickCallback
import com.squareup.picasso.Picasso

class RepositoryPullsAdapter(private val repositoryPullsClickCallback: RepositoryPullsItemClickCallback?) :
    RecyclerView.Adapter<RepositoryPullsAdapter.CarViewHolder>() {

    internal var repositoryList: List<Repositories>? = null
    internal var repositories: Repositories? = null
    internal var pullsItems: List<PullRequest>? = null

    fun setRepositories(repositories: Repositories) {
        if (this.repositories == null) {
            this.repositories = repositories
            notifyItemRangeInserted(0, repositories.items!!.size)
        }
    }

    fun setItems(pulls: List<PullRequest>) {
        if (this.pullsItems == null) {
            this.pullsItems = pulls
            notifyItemRangeInserted(0, pulls.size)
        }
    }

    fun setCarList(repositoryList: List<Repositories>) {
        if (this.repositoryList == null) {
            this.repositoryList = repositoryList
            notifyItemRangeInserted(0, repositoryList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@RepositoryPullsAdapter.repositoryList!!.size
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
        val binding: PullListItemClBinding =
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),  // R.layout.car_list_item,
                    R.layout.pull_list_item_cl,
                    parent, false
                )
        binding.callback = repositoryPullsClickCallback
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int
    ) {
        holder.binding.item = pullsItems!![position]
        // Log.d(TAG, "img: "+carList.get(position).imagem);
        Log.d(TAG, "pull item: " + pullsItems!![position])
        Picasso.get()
            .load(pullsItems!![position].user?.avatar_url)
            .placeholder(R.drawable.ic_car)
            .error(R.drawable.ic_alert)
            .into(holder.carImage)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (pullsItems == null) 0 else pullsItems!!.size
    }

    class CarViewHolder(val binding: PullListItemClBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val carImage: ImageView

        init {
            carImage = binding.pullContactIcon
        }
    }

    companion object {
        private val TAG = RepositoryPullsAdapter::class.java.simpleName
    }

}
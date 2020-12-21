package com.ccortez.desafioconcrete.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ccortez.desafioconcrete.R
import com.ccortez.desafioconcrete.databinding.PullListItemClBinding
import com.ccortez.desafioconcrete.model.PullRequest
import com.ccortez.desafioconcrete.model.Repositories
import com.ccortez.desafioconcrete.ui.callback.RepositoryPullsItemClickCallback
import com.squareup.picasso.Picasso

class RepositoryPullsAdapter(private val repositoryPullsClickCallback: RepositoryPullsItemClickCallback?) :
    RecyclerView.Adapter<RepositoryPullsAdapter.CarViewHolder>() {

    internal var repositories: Repositories? = null
    internal var pullsItems: List<PullRequest>? = null

    fun setItems(pulls: List<PullRequest>) {
        if (this.pullsItems == null) {
            this.pullsItems = pulls
            notifyItemRangeInserted(0, pulls.size)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarViewHolder {
        val binding: PullListItemClBinding =
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),
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
        Log.d(TAG, "pull item: " + pullsItems!![position])
        Picasso.get()
            .load(pullsItems!![position].user?.avatar_url)
            .placeholder(R.drawable.ic_user_placeholder)
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
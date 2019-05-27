package com.losingtimeapps.javahub.ui.home.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.losingtimeapps.javahub.R
import com.losingtimeapps.presentation.model.RepositoryModel
import com.losingtimeapps.presentation.ui.home.repository.RepositoryViewModel
import de.hdodenhof.circleimageview.CircleImageView

class RepositoriesListAdapter(val repositoryViewModel: RepositoryViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var repositorysList: MutableList<RepositoryModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewCounterLayoutItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryItemViewHolder(viewCounterLayoutItem)
    }

    override fun getItemCount(): Int {
        return repositorysList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryItemViewHolder) {
            val repository = repositorysList[position]
            holder.tvRepoName.text = repository.name
            holder.tvRepoDescription.text = repository.description
            holder.tvCountFork.text = repository.forksAmount
            holder.tvCountStar.text = repository.startAmount

            holder.tvOwnerName.text = repository.authorModel.name

            Glide.with(repositoryViewModel.getContext()).load(repository.authorModel.photoUrl)
                .apply(RequestOptions().error(R.drawable.ic_defauld_picture).placeholder(R.drawable.ic_defauld_picture))
                .into(holder.civOwnerPhoto)
        }
    }

    inner class RepositoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRepoName: TextView = itemView.findViewById(R.id.tv_repo_title)
        var tvRepoDescription: TextView = itemView.findViewById(R.id.tv_repo_description)
        var tvCountFork: TextView = itemView.findViewById(R.id.tv_count_fork)
        var tvCountStar: TextView = itemView.findViewById(R.id.tv_count_star)
        var tvOwnerName: TextView = itemView.findViewById(R.id.tv_owner_name)
        var civOwnerPhoto: CircleImageView = itemView.findViewById(R.id.civ_owner_photo)

        init {
            itemView.setOnClickListener {
                repositoryViewModel.onClickRepository(repositorysList[adapterPosition])
            }
        }
    }

    fun updateData(repositoryList: List<RepositoryModel>) {
        repositorysList.addAll(repositoryList)
        notifyDataSetChanged()
    }

    fun resetListAdapter() {
        repositorysList.clear()
        notifyDataSetChanged()
    }

}
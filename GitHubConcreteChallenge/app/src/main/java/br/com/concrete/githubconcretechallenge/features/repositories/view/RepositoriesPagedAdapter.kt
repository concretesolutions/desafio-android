package br.com.concrete.githubconcretechallenge.features.repositories.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.githubconcretechallenge.R
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cell_repository.view.*

class RepositoriesPagedAdapter
    : PagedListAdapter<RepositoryModel, RepositoriesPagedAdapter.ViewHolder>(DiffCallback) {

    var onItemClicked : ((RepositoryModel) -> (Unit))? = null

    private object DiffCallback : DiffUtil.ItemCallback<RepositoryModel>() {
        override fun areItemsTheSame(oldItem: RepositoryModel,
                                     newItem: RepositoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepositoryModel,
                                        newItem: RepositoryModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewFromLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_repository, parent, false)

        return ViewHolder(viewFromLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: RepositoryModel?) {
            if (data != null) {
                itemView.tv_repository_name.text = data.name
                itemView.tv_repository_description.text = data.description
                itemView.tv_repository_forks.text = data.forksCount
                itemView.tv_repository_stars.text = data.starsCount
                itemView.tv_repository_user_login.text = data.owner.login
                Glide
                    .with(itemView.context)
                    .load(data.owner.avatarUrl)
                    .centerCrop()
                    .into(itemView.iv_repository_user_avatar)

                itemView.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }

    }
}
package br.com.concrete.githubconcretechallenge.features.pullrequests.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.githubconcretechallenge.R
import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cell_pull_request.view.*

class PullRequestsAdapter :
    ListAdapter<PullRequestModel, PullRequestsAdapter.ViewHolder>(DiffCallback) {

    var onItemClicked : ((PullRequestModel) -> (Unit))? = null

    private object DiffCallback : DiffUtil.ItemCallback<PullRequestModel>() {
        override fun areItemsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewFromLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_pull_request, parent, false)

        return ViewHolder(viewFromLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: PullRequestModel) {
            itemView.tv_pull_request_title.text = data.title
            itemView.tv_pull_request_description.text = data.body
            itemView.tv_pull_request_user_login.text = data.user.login
            Glide
                .with(itemView.context)
                .load(data.user.avatarUrl)
                .centerCrop()
                .into(itemView.iv_pull_request_user_avatar)

            itemView.setOnClickListener {
                onItemClicked?.invoke(data)
            }

        }
    }


}

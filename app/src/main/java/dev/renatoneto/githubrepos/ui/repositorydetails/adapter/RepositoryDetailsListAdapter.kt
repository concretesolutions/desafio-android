package dev.renatoneto.githubrepos.ui.repositorydetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.renatoneto.githubrepos.databinding.RepositoryDetailsListItemBinding
import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import kotlinx.android.synthetic.main.repository_details_list_item.view.*

class RepositoryDetailsListAdapter(private val onClick: (GithubPullRequest) -> Unit) :
    RecyclerView.Adapter<RepositoryDetailsListAdapter.ViewHolder>() {

    private var items: ArrayList<GithubPullRequest> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: RepositoryDetailsListItemBinding = RepositoryDetailsListItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return ViewHolder(binding)

    }

    fun setItems(items: ArrayList<GithubPullRequest>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        Glide.with(holder.itemView.context)
            .load(items[position].user.avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.imgOwnerAvatar)

        holder.itemView.setOnClickListener {
            onClick(items[position])
        }
    }

    class ViewHolder(var binding: RepositoryDetailsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pullRequest: GithubPullRequest) {
            binding.pullRequest = pullRequest
            binding.executePendingBindings()
        }

    }

}

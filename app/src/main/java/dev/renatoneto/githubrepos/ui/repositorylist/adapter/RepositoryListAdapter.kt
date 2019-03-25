package dev.renatoneto.githubrepos.ui.repositorylist.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.renatoneto.githubrepos.databinding.RepositoryListItemBinding
import dev.renatoneto.githubrepos.model.github.GithubRepository
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoryListAdapter(private val onClick: (GithubRepository) -> Unit) :
    RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private var items: ArrayList<GithubRepository> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: RepositoryListItemBinding = RepositoryListItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return ViewHolder(binding)

    }

    fun setItems(items: ArrayList<GithubRepository>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        Glide.with(holder.itemView.context)
            .load(items[position].owner.avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.imgOwnerAvatar)

        holder.itemView.setOnClickListener {
            onClick(items[position])
        }
    }

    class ViewHolder(var binding: RepositoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: GithubRepository) {
            binding.repository = repository
            binding.executePendingBindings()
        }
    }

}

package cl.carteaga.querygithub.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cl.carteaga.querygithub.PullRequestActivity
import cl.carteaga.querygithub.R
import cl.carteaga.querygithub.utils.inflate
import cl.carteaga.querygithub.models.ItemsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_repository.view.*

class AdapterRepository(var data: MutableList<ItemsItem>) :
    RecyclerView.Adapter<AdapterRepository.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_repository))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
        holder.bindView(data.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(repository: ItemsItem) {
            itemView.txtNameRepository.text = repository.name
            itemView.txtAuthorName.text = repository.owner?.login
            itemView.txtDescriptionRepository.text = repository.name
            itemView.txtDescriptionRepository.text = repository.description
            itemView.txtNumberForks.text = repository.forksCount.toString()
            itemView.txtNumberStarts.text = repository.stargazersCount.toString()
            Glide.with(itemView.context)
                .load(repository.owner?.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.imgAvatar)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PullRequestActivity::class.java)
                intent.putExtra("nameRepository", it.txtNameRepository.text)
                intent.putExtra("authorName", it.txtAuthorName.text)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun add(repository: ItemsItem?)
    {
        repository?.let { data.add(it) }
        notifyItemInserted(data.size - 1)
    }

    fun add(repositories: List<ItemsItem?>?)
    {
        if (repositories != null) {
            for(repository in repositories)
            {
                add(repository)
            }
        }
    }
}
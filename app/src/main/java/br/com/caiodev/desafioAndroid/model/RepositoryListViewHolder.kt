package br.com.caiodev.desafioAndroid.model

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.desafioAndroid.interfaces.RecyclerViewOnClick
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repo_item_recycler_view_layout.view.*

class RepositoryListViewHolder(
    itemView: View,
    private val recyclerViewOnClick: RecyclerViewOnClick<RepositoryItemModel>
) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: RepositoryItemModel) {

        itemView.repositoryName.text = model.repoName
        itemView.repositoryDescription.text = model.description

        model.repoOwner?.avatarUrl?.let { url ->
            loadImage(itemView.context, url, itemView.userImage)
        }

        itemView.userNameTextView.text = model.repoOwner?.login
        itemView.ownerNameTextView.text = model.repoOwner?.userFullName
        itemView.numberOfForks.text = model.numberOfForks.toString()
        itemView.ownerNameTextView.text = model.repoOwner?.userFullName
        itemView.numberOfStarsTextView.text = model.numberOfStars.toString()

        itemView.setOnClickListener {
            recyclerViewOnClick.onItemClickListener(itemView, adapterPosition, model)
        }
    }

    private fun loadImage(context: Context, url: String, target: ImageView) {
        Glide.with(context)
            .load(url)
            .into(target)
    }
}
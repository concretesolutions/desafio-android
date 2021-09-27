package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.databinding.ItemRepositoryBinding

class RepositoryViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRepositoryBinding.bind(view)

    fun bind(item: RepositoryEntity) {
        binding.txtUsername.text = item.username
        binding.txtUserName.text = item.userName
        binding.txtRepositoryName.text = item.repositoryName
        binding.txtRepositoryDescription.text = item.repositoryDescription
        binding.txtForksAmount.text = item.forksAmount.toString()
        binding.txtStarGazersAmount.text = item.starsAmount.toString()
    }

}
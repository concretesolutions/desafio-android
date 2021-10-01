package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.databinding.ItemRepositoryBinding
import com.concrete.challenge.presentation.model.RepositoryItem
import com.concrete.challenge.ui.adapters.RepositoryAdapter

class RepositoryViewHolder(
    view: View,
    private val manager: RepositoryAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRepositoryBinding.bind(view)

    fun bind(item: RepositoryItem) {
        binding.txtUsername.text = item.repositoryOwner.username
        binding.txtUserName.text = item.repositoryOwner.userName
        binding.txtRepositoryName.text = item.repositoryName
        binding.txtRepositoryDescription.text = item.repositoryDescription
        binding.txtForksAmount.text = item.forksAmount.toString()
        binding.txtStarGazersAmount.text = item.starsAmount.toString()

        binding.repositoryCard.setOnClickListener {
            manager.onRepositoryClicked(item)
        }
    }

}
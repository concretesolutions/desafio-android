package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.databinding.ItemRepositoryBinding
import com.concrete.challenge.presentation.model.RepositoryItem
import com.concrete.challenge.ui.adapters.RepositoryAdapter
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


class RepositoryViewHolder(
    private val view: View,
    private val manager: RepositoryAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRepositoryBinding.bind(view)

    fun bind(item: RepositoryItem) {

        Picasso.get().load(item.repositoryOwner.userAvatarUrl).into(binding.ivImageUser)
        
        val formatter = DecimalFormat("#,###")
        val forksAmount = item.forksAmount
        val starsAmount = item.starsAmount

        binding.txtUsername.text = item.repositoryOwner.username
        binding.txtUserName.text = item.repositoryOwner.userName
        binding.txtRepositoryName.text = item.repositoryName
        binding.txtRepositoryDescription.text = item.repositoryDescription
        binding.txtForksAmount.text = formatter.format(forksAmount)
        binding.txtStarGazersAmount.text = formatter.format(starsAmount)

        binding.repositoryCard.setOnClickListener {
            manager.onRepositoryClicked(item)
        }
    }
}
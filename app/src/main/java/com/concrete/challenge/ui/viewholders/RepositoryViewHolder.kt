package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.databinding.ItemRepositoryBinding
import com.concrete.challenge.ui.adapters.RepositoryAdapter

class RepositoryViewHolder(
    private val view: View,
    private val manager: RepositoryAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRepositoryBinding.bind(view)

    fun bind(item: RepositoryEntity) {
        binding.txtUsername.text = item.username
        binding.txtUserName.text = item.userName
        binding.txtRepositoryName.text = item.repositoryName
        binding.txtRepositoryDescription.text = item.repositoryDescription
        binding.txtForksAmount.text = item.forksAmount.toString()
        binding.txtStarGazersAmount.text = item.starsAmount.toString()

        view.setOnClickListener {
            manager.onRepositoryClicked(item)
        }
    }

}
package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.databinding.ItemPullRequestBinding
import com.concrete.challenge.ui.adapters.PullRequestAdapter
import com.concrete.challenge.ui.fragments.PullRequestFragment

class PullRequestViewHolder(
    private val view: View,
    private val manager: PullRequestAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPullRequestBinding.bind(view)

    fun bind(item: PullRequestEntity) {
        //binding.txtOpenPullRequestAmount.text = item.openPullRequestAmount.toString()
        //binding.txtClosedPullRequestAmount.text = item.closedPullRequestAmount.toString()
        binding.txtPullRequestTitle.text = item.pullRequestTitle
        binding.txtPullRequestBody.text = item.pullRequestBody
        binding.txtUsername.text = item.username
        binding.txtUserName.text = item.userName

        binding.pullRequestCard.setOnClickListener {
            manager.onPullRequestClicked(item)
        }
    }
}
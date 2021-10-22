package com.concrete.challenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.databinding.ItemPullRequestBinding
import com.concrete.challenge.ui.adapters.PullRequestAdapter
import com.squareup.picasso.Picasso

const val emptyBody = "<< No tiene descripción >>"

class PullRequestViewHolder(
    private val view: View,
    private val manager: PullRequestAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPullRequestBinding.bind(view)

    fun bind(item: PullRequestEntity) {
        //binding.txtOpenPullRequestAmount.text = item.openPullRequestAmount.toString()
        //binding.txtClosedPullRequestAmount.text = item.closedPullRequestAmount.toString()

        Picasso.get().load(item.userInfo.userAvatarUrl).into(binding.ivUser)

        binding.txtPullRequestTitle.text = item.pullRequestTitle

        when(item.pullRequestBody == "") {
            true -> binding.txtPullRequestBody.text = emptyBody
            false -> binding.txtPullRequestBody.text = item.pullRequestBody
        }

        binding.txtUsername.text = item.userInfo.username
        binding.txtUserName.text = item.userInfo.userName

        binding.pullRequestCard.setOnClickListener {
            manager.onPullRequestClicked(item)
        }
    }
}
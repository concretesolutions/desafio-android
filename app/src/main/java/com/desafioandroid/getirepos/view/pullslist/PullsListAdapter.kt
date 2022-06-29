package com.desafioandroid.getirepos.view.pullslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.data.utils.PullsTranslator
import com.desafioandroid.getirepos.databinding.ActivityPullsItemBinding

class PullsListAdapter(private val listener: PullsListActivityListener): RecyclerView.Adapter<PullsListAdapter.PullsViewHolder>() {
    private var pulls = ArrayList<PullsItem>()
    interface PullsListActivityListener {
        fun pullSelected(pullLink: String)
    }

    fun setPullsItems(pullsList: List<PullsResponse>) {
        this.pulls.addAll(PullsTranslator.translatePullsToPullsItem(pullsList))
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityPullsItemBinding.inflate(layoutInflater, parent, false)
        return PullsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.pulls.size
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        holder.bind(this.pulls[position])
    }

    inner class PullsViewHolder(private val binding: ActivityPullsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pull: PullsItem) {
            this.binding.pullTitleText.text = pull.title
            this.binding.userNamePullsText.text = pull.user.login
            this.binding.pullDescriptionText.text = pull.body
            Glide.with(binding.root).load(pull.user.avatarUrl).into(this.binding.userAvatarPullsImageView)
            this.binding.root.setOnClickListener {
                listener.pullSelected(pull.htmlUrl)
            }
        }
    }
}
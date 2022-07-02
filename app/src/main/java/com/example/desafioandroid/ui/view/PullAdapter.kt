package com.example.desafioandroid.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.databinding.FragmentPullBinding

class PullAdapter() : ListAdapter<PullModel, PullAdapter.PullViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PullModel>() {
        override fun areItemsTheSame(
            oldItem: PullModel,
            newItem: PullModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PullModel,
            newItem: PullModel
        ): Boolean {
            return oldItem.id_pull === newItem.id_pull
        }
    }

    private var onItemClickListener: ((PullModel) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (PullModel) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullAdapter.PullViewHolder {
        val binding = FragmentPullBinding.inflate(LayoutInflater.from(parent.context))
        return PullViewHolder(binding)
    }

    override fun onBindViewHolder(pullViewHolder: PullAdapter.PullViewHolder, position: Int) {
        val pull = getItem(position)
        pullViewHolder.bind(pull)
    }

    inner class PullViewHolder(private val binding: FragmentPullBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pull: PullModel) {
            binding.ivAvatarUserPull.load(pull.user.avatarUrl)
            binding.tvPullName.text = pull.title
            binding.layoutPull.setOnClickListener {
                onItemClickListener?.invoke(pull)
            }
        }
    }
}

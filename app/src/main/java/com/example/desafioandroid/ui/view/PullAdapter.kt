package com.example.desafioandroid.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.desafioandroid.R
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.databinding.FragmentPullBinding

class PullAdapter : ListAdapter<PullModel, PullAdapter.PullViewHolder>(DiffCallback) {

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
            return oldItem == newItem
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
            binding.ivAvatarUserPull.load(pull.user.avatarUrl) {
                crossfade(true)
                    .placeholder(R.drawable.ic_launcher_background)
                    .transformations(CircleCropTransformation())
            }
            binding.tvPullBody.text = pull.body
            binding.tvPullName.text = pull.title
            binding.layoutPull.setOnClickListener {
                onItemClickListener?.invoke(pull)
            }
        }
    }
}

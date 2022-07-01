package com.example.desafioandroid.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.databinding.FragmentPullBinding


class PullRecyclerViewAdapter(
    private val pull: List<PullModel>,
    private val listener: PullSelectionListener
): RecyclerView.Adapter<PullRecyclerViewAdapter.PullViewHolder>() {

    interface PullSelectionListener {
        fun select(pullName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentPullBinding.inflate(layoutInflater,parent,false)
        return PullViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        holder.bind(this.pull[position])
    }

    override fun getItemCount(): Int {
        return this.pull.size
    }

    class PullViewHolder( private val binding: FragmentPullBinding, private val listener: PullSelectionListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(pull: PullModel) {
            binding.ivAvatarUserPull.load(pull.user.avatar_url_owner)
            binding.tvPullName.text = pull.title
            this.binding.tvPullName.setOnClickListener{
                listener.select(binding.tvPullName.text.toString())
            }
        }
    }
}

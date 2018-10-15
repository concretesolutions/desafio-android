package com.rafaelpereiraramos.desafioAndroid.view.pull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelpereiraramos.desafioAndroid.R
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
class PullPagedListAdapter : PagedListAdapter<PullTO, PullPagedListAdapter.PullViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PullTO>() {
            override fun areItemsTheSame(oldItem: PullTO, newItem: PullTO): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PullTO, newItem: PullTO): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder = PullViewHolder.create(parent)

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) = holder.bind(getItem(position))

    class PullViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun create(parent: ViewGroup): PullPagedListAdapter.PullViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_pull_list, parent, false)
                return PullPagedListAdapter.PullViewHolder(view)
            }
        }

        val _pullTitle: TextView = view.findViewById(R.id.pull_title)
        val _pullDesc: TextView = view.findViewById(R.id.pull_description)
        val _ownerImg: ImageView = view.findViewById(R.id.pull_img)
        val _ownerName: TextView = view.findViewById(R.id.owner_name)

        private var pull: PullTO? = null

        fun bind(pull: PullTO?) {
            this.pull= pull
            showData(pull!!)
        }

        private fun showData(pull: PullTO) {
            _pullTitle.text = pull.title
            _pullDesc.text = pull.body
            _ownerName.text = pull.user!!.login

            Glide.with(_ownerImg).load(pull.user!!.avatarUrl).into(_ownerImg)
        }
    }
}
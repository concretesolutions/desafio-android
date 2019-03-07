package com.example.lucasdias.mvvmpoc.presentation.ui.repositories

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lucasdias.mvvmpoc.R
import com.example.lucasdias.mvvmpoc.domain.entity.Repository
import com.example.lucasdias.mvvmpoc.presentation.extensions.loadUrl
import timber.log.Timber

class RepositoryAdapter(var view: RepositoryActivity): Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositoryList = mutableListOf<Repository>()

    fun updateRepositoryList(repositoryList: List<Repository>) {

        if (this.repositoryList.isNotEmpty()) {
            this.repositoryList.clear()
        }
        this.repositoryList.addAll(repositoryList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.repository_list_item, parent, false)
        return ViewHolder(viewLayout)
     }

    override fun getItemCount(): Int {
        return repositoryList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (repositoryList.isNotEmpty()) {
            holder.bind(repositoryList[position], view, position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title by lazy { itemView.findViewById<TextView>(R.id.repositoryListItem_tvTitle) }
        private val description by lazy { itemView.findViewById<TextView>(R.id.repositoryListItem_tvDescription) }
        private val starCount by lazy { itemView.findViewById<TextView>(R.id.repositoryListItem_starCount) }
        private val forkCount by lazy { itemView.findViewById<TextView>(R.id.repositoryListItem_forkCount) }
        private val ownerName by lazy { itemView.findViewById<TextView>(R.id.repositoryListItem_tvOwnerName) }
        private val coloredLine by lazy { itemView.findViewById<View>(R.id.repositoryListItem_vwColoredLine) }
        private val ownerPicture by lazy { itemView.findViewById<ImageView>(R.id.repositoryListItem_ivOwnerPicture) }

        fun bind(repository: Repository, view: RepositoryActivity, position: Int) {

            val color = getColor(position)

            title.text = repository.name
            description.text = repository.description
            starCount.text = repository.stargazersCount.toString()
            forkCount.text = repository.forksCount.toString()
            ownerName.text = repository.user?.login
            coloredLine.setBackgroundResource(color)
            ownerPicture.loadUrl(repository.user?.avatarUrl)

            itemView.setOnClickListener {
                Timber.i("Repository clicked.")
                view.callPullRequestActivity(repository.fullName, repository.id, color, repository.name)
            }
        }

        private fun getColor(position: Int): Int {
            val colorPosition: Int = if (position < 4) {
                position + 4
            } else{
                position
            }
            var color = R.color.pink

            when (colorPosition % 4) {
                0 -> color = R.color.lightGreen
                1 -> color = R.color.lightYellow
                2 -> color = R.color.darkBlue
                3 -> color = R.color.pink
            }

            return color
        }

    }
}
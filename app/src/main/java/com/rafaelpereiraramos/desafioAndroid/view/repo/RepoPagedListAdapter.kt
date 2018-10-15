package com.rafaelpereiraramos.desafioAndroid.view.repo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelpereiraramos.desafioAndroid.R
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import com.rafaelpereiraramos.desafioAndroid.view.pull.PullActivity

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
class RepoPagedListAdapter : PagedListAdapter<RepoTO, RepoPagedListAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RepoTO>() {
            override fun areItemsTheSame(oldItem: RepoTO, newItem: RepoTO): Boolean =
                    oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: RepoTO, newItem: RepoTO): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder = RepoViewHolder.create(parent)

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoItem = getItem(position)
        holder.bind(repoItem)
    }

    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun create(parent: ViewGroup): RepoViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_repo_list, parent, false)
                return RepoViewHolder(view)
            }
        }

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, PullActivity::class.java)

                intent.putExtra(PullActivity.REP, repo!!)

                view.context.startActivity(intent)
            }
        }

        private val _repoTitle: TextView = view.findViewById(R.id.repo_title)
        private val _repoDescription: TextView = view.findViewById(R.id.repo_description)
        private val _starts: Button = view.findViewById(R.id.stargazers)
        private val _forks: Button = view.findViewById(R.id.forks)
        private val _repoImg: ImageView = view.findViewById(R.id.repo_img)
        private val _ownerLogin: TextView = view.findViewById(R.id.owner_name)

        private var repo: RepoTO? = null

        fun bind(repo: RepoTO?) {
            this.repo = repo
            showRepoData(repo!!)
        }

        private fun showRepoData(repo: RepoTO) {
            _repoTitle.text = repo.fullName
            _repoDescription.text = repo.description

            _starts.text = repo.stargazers.toString()
            _forks.text = repo.forks.toString()

            _ownerLogin.text = repo.owner.login

            Glide.with(_repoImg).load(repo.owner.avatarUrl).into(_repoImg)
        }
    }
}
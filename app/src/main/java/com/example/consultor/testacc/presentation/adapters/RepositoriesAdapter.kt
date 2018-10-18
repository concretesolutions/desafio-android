package com.example.consultor.testacc.presentation.adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.consultor.testacc.R
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.presentation.activities.RepoDetailActivity
import kotlinx.android.synthetic.main.item_repo_card.view.*

class RepositoriesAdapter(val context: Context, diffCallback: DiffUtil.ItemCallback<Repository>) :
    PagedListAdapter<Repository, RepositoriesAdapter.RepositoryViewHolder>(diffCallback) {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repo_card, p0, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(p0: RepositoryViewHolder, p1: Int) {

        val repo = getItem(p1)
        if (repo != null)
            p0.bindView(repo)
    }


    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(repository: Repository) {
            Glide.with(itemView.context).load(repository.owner.avatar).apply(RequestOptions().circleCrop())
                .into(itemView.cv_avatar)
            itemView.cv_owner_name.text = repository.owner.name
            itemView.cv_repo_name.text = repository.repoName
            itemView.cv_repo_desc.text = repository.repoDesc
            itemView.cv_repo_forks.text = repository.reposForks.toString()
            itemView.cv_repo_stars.text = repository.reposStars.toString()

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("repopull", repository)
                itemView.context.startActivity(
                    Intent(itemView.context, RepoDetailActivity::class.java).putExtra("mybun", bundle)
                )
            }

        }

    }
}
package br.com.renan.desafioandroid.repository.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.Repository
import br.com.renan.desafioandroid.pullrequest.view.PullRequestActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(repositoryItemsList: List<Repository>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recyclerList: List<Repository> = repositoryItemsList

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: Repository) = with(itemView) {

            Glide.with(itemView.context)
                .load(repository.owner.avatarUrl)
                .into(ivRepositoryAvatar)

            tvRepositoryName.text = repository.name
            tvRepositoryDescription.text = repository.description
            tvRepositoryForks.text = repository.forks.toString()
            tvRepositoryStars.text = repository.starts.toString()
            tvRepositoryUserName.text = repository.owner.login
            tvRepositoryFullName.text = repository.fullName.replace("/", " ")

            itemView.setOnClickListener { itemView ->
                val intent = Intent(itemView.context, PullRequestActivity::class.java)
                intent.putExtra("creator", repository.owner.login)
                intent.putExtra("repository", repository.name)
                context.startActivity(intent)
            }
        }
    }
}
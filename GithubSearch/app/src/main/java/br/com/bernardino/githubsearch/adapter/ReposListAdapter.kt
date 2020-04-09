package br.com.bernardino.githubsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.bernardino.githubsearch.R
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_repos.view.*


class ReposListAdapter(private val mContext: Context, val mClickListener: (RepositoryDatabase) -> Unit)
    : RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {

    var repos : List<RepositoryDatabase> = listOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.let {
            it.bindView(repo, mContext, mClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item_repos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun setReposListItems(repoList: List<RepositoryDatabase>){
        this.repos = repoList
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            repo: RepositoryDatabase,
            context: Context,
            clickListener: (RepositoryDatabase) -> Unit
        ) {

            val repoName = itemView.tv_repos_name
            val repoDesc = itemView.tv_repos_desc
            val forkNumber = itemView.tv_fork_number
            val starNumber = itemView.tv_star_number
            val userAvatar = itemView.ci_user_avatar
            val userName = itemView.tv_user_name
            val userFullName = itemView.tv_user_full_name

            repoName.text = repo.name
            repoDesc.text = repo.description
            forkNumber.text = repo.forksCount.toString()
            starNumber.text = repo.stargazersCount.toString()
            Glide.with(context).load(repo.ownerAvatar).into(userAvatar)
            userName.text = repo.ownerUserFirstName
            userFullName.text = repo.ownerUserLastName

            itemView.setOnClickListener{
                clickListener(repo)
            }
        }
    }

}


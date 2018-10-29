package br.com.andreyneto.desafioandroid.ui.repos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.model.Repo
import kotlinx.android.synthetic.main.list_repo.view.*

class RepoAdapter(private val repos: List<Repo>,
                  private val ctx: Context,
                  private val presenter: ReposContract.Presenter) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.list_repo, p0, false), presenter)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindView(repos[p1])
    }

    class ViewHolder(itemView: View, val presenter: ReposContract.Presenter): RecyclerView.ViewHolder(itemView) {
        fun bindView(repo: Repo) {
            itemView.lblOwner.text = "${repo.owner.name}/"
            itemView.lblRepoTitle.text = repo.name
            itemView.lblRepoDescripition.text = repo.description
            itemView.lblFork.text = repo.forks.toString()
            itemView.lblStars.text = repo.stars.toString()
            itemView.setOnClickListener { presenter.openRepo(itemView.context,  repo) }
            itemView.isClickable = true
            itemView.separator.visibility = View.VISIBLE
        }
    }
}
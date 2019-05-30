package cl.jesualex.desafio_android.repo.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * Created by jesualex on 2019-05-30.
 */
class RepoAdapter: RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    var repos: List<Repo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_repo, p0, false))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.initView(repos[p1], p1, repos.isNotEmpty() && p1 == repos.size -1)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal fun initView(repo: Repo, pos: Int, isLast: Boolean){
            itemView.name.text = repo.name
        }
    }
}
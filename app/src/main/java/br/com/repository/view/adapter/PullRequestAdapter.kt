package br.com.repository.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.repository.R
import br.com.repository.databinding.CardviewPullRequestBinding
import br.com.repository.model.PullRequest
import br.com.repository.view.holder.PullRequestHolder

class PullRequestAdapter(private val onClick: (PullRequest) -> Unit): RecyclerView.Adapter<PullRequestHolder>() {

    private var list: List<PullRequest> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val cardviewPullRequestBinding =
                DataBindingUtil.inflate<CardviewPullRequestBinding>(layoutInflater, R.layout.cardview_pull_request,
                        parent, false)
        return PullRequestHolder(cardviewPullRequestBinding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PullRequestHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    fun setListPullRequest(list: List<PullRequest>) {
        this.list = list
        notifyDataSetChanged()
    }
}
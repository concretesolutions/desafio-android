package br.com.repository.view.holder

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import br.com.repository.databinding.CardviewPullRequestBinding
import br.com.repository.model.PullRequest

class PullRequestHolder(itemView: CardviewPullRequestBinding): RecyclerView.ViewHolder(itemView.root) {

    val card: CardviewPullRequestBinding = DataBindingUtil.bind(itemView.root)!!

    fun bind(pull: PullRequest, onClick: (PullRequest) -> Unit){
        card.pullRequest = pull
        card.executePendingBindings()

        itemView.setOnClickListener {
            onClick(pull)
        }
    }

}
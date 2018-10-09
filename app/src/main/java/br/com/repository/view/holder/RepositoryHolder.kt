package br.com.repository.view.holder

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import br.com.repository.databinding.CardviewRepositoryBinding
import br.com.repository.model.Repository

class RepositoryHolder(itemView: CardviewRepositoryBinding) : RecyclerView.ViewHolder(itemView.root) {
    val card: CardviewRepositoryBinding = DataBindingUtil.bind(itemView.root)!!

    fun bind(repository: Repository, onClick: (Repository) -> Unit) {
        card.repository = repository
        card.executePendingBindings()

        itemView.setOnClickListener {
            onClick(repository)
        }
    }
}
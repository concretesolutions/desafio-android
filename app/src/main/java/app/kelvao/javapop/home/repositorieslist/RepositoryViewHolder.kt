package app.kelvao.javapop.home.repositorieslist

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import app.kelvao.javapop.databinding.HolderHomeRepositoriesBinding
import app.kelvao.javapop.domain.network.response.RepositoryResponse

class RepositoryViewHolder(
    private val binding: HolderHomeRepositoriesBinding,
    private val onClickRepository: ((RepositoryViewHolder) -> Unit)? = null
) : ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onClickRepository?.invoke(this) }
    }

    fun bind(repository: RepositoryResponse) {
        binding.repository = repository
        binding.executePendingBindings()
    }

}
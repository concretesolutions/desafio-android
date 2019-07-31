package app.kelvao.javapop.home.repositorieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.kelvao.javapop.R
import app.kelvao.javapop.domain.network.response.RepositoryResponse

class RepositoriesDataSource(
    private var data: MutableList<RepositoryResponse> = mutableListOf(),
    val onClickRepository: ((RepositoryViewHolder) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val loaderPosition
        get() = data.size

    var isLoading: Boolean = false
        set(value) {
            field = value
            notifyItemChanged(loaderPosition)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == REPOSITORIES_VIEW_TYPE) {
            RepositoryViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.holder_home_repositories, parent, false
                ),
                onClickRepository
            )
        } else {
            ProgressbarViewHolder(parent)
        }

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int =
        if (position == loaderPosition) LOADER_VIEW_TYPE else REPOSITORIES_VIEW_TYPE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) {
            holder.bind(data[position])
        } else if (holder is ProgressbarViewHolder) {
            holder.visibility = isLoading
        }
    }

    fun setRepositories(repositories: List<RepositoryResponse>) {
        data = repositories.toMutableList()
        notifyDataSetChanged()
    }

    fun addRepositories(repositories: List<RepositoryResponse>) {
        val lastSize = data.size
        data.addAll(repositories.toMutableList())
        notifyItemChanged(lastSize, data.size)
    }

    companion object {
        private const val LOADER_VIEW_TYPE = 0
        private const val REPOSITORIES_VIEW_TYPE = 1
    }
}
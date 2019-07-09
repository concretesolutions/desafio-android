package app.kelvao.javapop.home.repositorieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.kelvao.javapop.R
import app.kelvao.javapop.domain.network.response.RepositoryResponse

class RepositoriesDataSource(
    private val data: MutableList<RepositoryResponse> = mutableListOf(),
    val onClickRepository: ((RepositoryViewHolder) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RepositoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_home_repositories, parent, false
            ),
            onClickRepository
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) {
            holder.bind(data[position])
        }
    }

}
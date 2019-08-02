package matheusuehara.github.features.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import matheusuehara.github.R
import matheusuehara.github.data.model.Repository

class RepositoryAdapter(var repositories: ArrayList<Repository>, var repositoryClickListener: RepositoryClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading = false

    companion object {
        private const val REPOSITORY_TYPE = 0
        private const val LOADING_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == REPOSITORY_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_item_repository, parent, false)
            RepositoryViewHolder(view, repositoryClickListener)
        }else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_loading, parent, false)
            RepositoryLoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) holder.bindViewHolder(repositories[position])
    }

    fun addRepositories(newRepositories: ArrayList<Repository>) {
        repositories.addAll(newRepositories)
        this.notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= repositories.size) LOADING_TYPE else REPOSITORY_TYPE
    }

    override fun getItemCount(): Int {
        return if (isLoading) repositories.size + 2 else repositories.size
    }

    fun startLoading() {
        isLoading = true
        notifyDataSetChanged()
    }

    fun stopLoading() {
        isLoading = false
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean {
        return isLoading
    }

}
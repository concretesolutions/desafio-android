package cl.mauledev.github.view.lists.adapters

import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import cl.mauledev.github.R
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.utils.Constants
import cl.mauledev.github.view.lists.viewholders.RepoViewHolder
import cl.mauledev.github.view.viewmodels.MainViewModel

class ReposAdapter(private val viewModel: MainViewModel?) : ListAdapter<Repo, RepoViewHolder>(differCallback) {

    companion object {

        val differCallback = object: DiffUtil.ItemCallback<Repo>() {

            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun getChangePayload(oldItem: Repo, newItem: Repo): Any? {

                if (oldItem.id != newItem.id) {
                    return Bundle().apply {
                        putParcelable(Constants.REPO, newItem)
                    }
                }

                return super.getChangePayload(oldItem, newItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.init(getItem(position))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else {
            val bundle = payloads[0] as Bundle
            holder.init(bundle.getParcelable(Constants.REPO))
        }
    }
}
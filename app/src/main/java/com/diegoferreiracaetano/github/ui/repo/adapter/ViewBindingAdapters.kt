package com.diegoferreiracaetano.github.ui.repo.adapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.repo.Repo

object ViewBindingAdapters{
    @JvmStatic
    @BindingAdapter("repoAdapter","repoRetry","repoCallback","repoNetworkEvents", requireAll= false)
    fun RecyclerView.setReviewAdapter(items: PagedList<Repo>?, retryCallback: () -> Unit,
                                      listener: RepoViewHolder.OnItemClickListener,
                                      networkState: NetworkState?) {
        items?.let {
            if(adapter == null)
                adapter = RepoAdapter(retryCallback,listener)
            (adapter as RepoAdapter).submitList(items)
            (adapter as RepoAdapter).setNetworkState(networkState)

        }
    }
}
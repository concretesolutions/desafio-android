package com.example.desafioandroid.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.CardRepositoryBinding
import com.example.desafioandroid.schema.RepositoryItem
import com.example.desafioandroid.util.DiffUtilCallBack
import com.example.desafioandroid.viewModel.itemAdapter.ItemRepositoryViewModel

class RepositoryAdapter(private val fragmentManager: FragmentManager) : PagedListAdapter<RepositoryItem, RepositoryAdapter.RepositoryAdapterViewHolder>(
    DiffUtilCallBack()
) {
    val TAG = javaClass.simpleName
    private var repositoryList: List<RepositoryItem>? = null

    override fun getItemCount(): Int {
        if (repositoryList == null)
            return 0
        return repositoryList!!.size
    }

    internal fun setRepositoryList(repositoryList: List<RepositoryItem>) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepositoryAdapterViewHolder, position: Int) {
        Log.e(TAG,repositoryList!![position].name)
        getItem(position)?.let { holder.bindRepository(it,fragmentManager) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapterViewHolder {
        val itemPeopleBinding: CardRepositoryBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.card_repository,
            parent, false)

        return RepositoryAdapterViewHolder(itemPeopleBinding)
    }

    class RepositoryAdapterViewHolder(
        var mItemRepositoryBinding: CardRepositoryBinding
    ) :
        RecyclerView.ViewHolder(mItemRepositoryBinding.cardRepository) {
        val TAG = javaClass.simpleName

        fun bindRepository(repositoryItem: RepositoryItem, fragmentManager: FragmentManager) {
            mItemRepositoryBinding.repositoryViewModel =
                ItemRepositoryViewModel(
                    repositoryItem,
                    fragmentManager
                )
        }
    }
}

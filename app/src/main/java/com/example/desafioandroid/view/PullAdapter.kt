package com.example.desafioandroid.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.CardPullBinding
import com.example.desafioandroid.schemas.PullItem
import com.example.desafioandroid.viewModel.ItemPullViewModel

class PullAdapter : RecyclerView.Adapter<PullAdapter.PullAdapterViewHolder>() {
    val TAG = javaClass.simpleName
    private var pullList: List<PullItem>? = null

    override fun getItemCount(): Int {
        if (pullList == null)
            return 0
        return pullList!!.size
    }

    internal fun setRepositoryList(pullList: List<PullItem>) {
        this.pullList = pullList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PullAdapter.PullAdapterViewHolder, position: Int) {
        holder.bindPull(pullList!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullAdapterViewHolder {
        val itemPeopleBinding: CardPullBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.card_pull,
            parent, false)

        return PullAdapterViewHolder(itemPeopleBinding)
    }

    class PullAdapterViewHolder(
        var mItemPullBinding: CardPullBinding
    ) :
        RecyclerView.ViewHolder(mItemPullBinding.cardPull) {

        fun bindPull(pullItem: PullItem) {
            if (mItemPullBinding.pullViewModel == null) {
                mItemPullBinding.pullViewModel = ItemPullViewModel(pullItem)

            } else {
                mItemPullBinding.pullViewModel!!.mPullItem = pullItem
            }
        }
    }
}

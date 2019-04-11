package com.example.desafioandroid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.CardPullBinding
import com.example.desafioandroid.schema.PullItem
import com.example.desafioandroid.viewModel.itemAdapter.ItemPullViewModel

class PullAdapter : RecyclerView.Adapter<PullAdapter.PullAdapterViewHolder>() {
    val TAG = javaClass.simpleName
    private var pullList: List<PullItem>? = null

    override fun getItemCount(): Int {
        if (pullList == null)
            return 0
        return pullList!!.size
    }

    internal fun setPullList(pullList: List<PullItem>) {
        this.pullList = pullList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PullAdapterViewHolder, position: Int) {
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
            mItemPullBinding.pullViewModel =
                ItemPullViewModel(pullItem)
        }
    }
}

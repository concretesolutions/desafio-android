package br.com.caiodev.desafioAndroid.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.desafioAndroid.R
import br.com.caiodev.desafioAndroid.interfaces.RecyclerViewDataSource
import br.com.caiodev.desafioAndroid.interfaces.RecyclerViewOnClick

class RepositoryListAdapter(
    private val data: RecyclerViewDataSource<RepositoryItemModel>,
    private val recyclerViewOnClick: RecyclerViewOnClick<RepositoryItemModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data.getViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepositoryListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repo_item_recycler_view_layout,
                parent,
                false
            )
            , recyclerViewOnClick
        )
    }

    override fun getItemCount(): Int {
        return data.getTotalCount()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (viewHolder) {
            is RepositoryListViewHolder -> viewHolder.bind(data.getItem(position))
        }
    }
}
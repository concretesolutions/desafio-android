package com.pedrenrique.githubapp.core.platform

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class Adapter(
    private val typesFactory: TypesFactory
) : RecyclerView.Adapter<BaseViewHolder<ModelHolder>>() {

    var items: List<ModelHolder> = emptyList()
        private set

    private var dataVersion = 0

    override fun onCreateViewHolder(parent: ViewGroup, layout: Int): BaseViewHolder<ModelHolder> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(layout, parent, false)
        return getViewHolderForLayout(layout, view)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewHolderForLayout(layout: Int, view: View) =
        typesFactory.holder(layout, view) as BaseViewHolder<ModelHolder>

    override fun onBindViewHolder(holder: BaseViewHolder<ModelHolder>, position: Int) {
        holder.bind(getItemForPosition(position), typesFactory)
    }

    override fun getItemViewType(position: Int) =
        items[position].type(typesFactory)

    override fun getItemCount() =
        items.size

    @MainThread
    fun replace(update: List<ModelHolder>) {
        dataVersion++
        when {
            items.isEmpty() -> {
                if (update.isEmpty()) return
                items = update
                notifyDataSetChanged()
            }
            update.isEmpty() -> {
                val oldSize = items.size
                items = listOf()
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> Task(dataVersion, items, update).execute()
        }
    }

    private fun getItemForPosition(position: Int) =
        items[position]

    open fun areItemsTheSame(oldItem: Any?, newItem: Any?) = oldItem == newItem

    open fun areContentsTheSame(oldItem: Any?, newItem: Any?) = oldItem == newItem

    @SuppressLint("StaticFieldLeak")
    inner class Task(
        private val startVersion: Int,
        private val oldItems: List<ModelHolder>,
        private val newItems: List<ModelHolder>
    ) : AsyncTask<Void, Void, DiffUtil.DiffResult>() {

        override fun doInBackground(vararg voids: Void?): DiffUtil.DiffResult =
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize() = oldItems.size

                override fun getNewListSize() = newItems.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    this@Adapter.areItemsTheSame(
                        oldItems[oldItemPosition],
                        newItems[newItemPosition]
                    )

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    this@Adapter.areContentsTheSame(
                        oldItems[oldItemPosition],
                        newItems[newItemPosition]
                    )
            })

        override fun onPostExecute(result: DiffUtil.DiffResult) {
            if (startVersion == dataVersion) {
                items = newItems
                result.dispatchUpdatesTo(this@Adapter)
            }
        }
    }
}
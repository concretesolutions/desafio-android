package br.com.concrete.desafio.adapter

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseRecyclerAdapter<T : Parcelable> : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val STATE_LIST = "STATE_LIST"
    }

    val items: MutableList<T> = mutableListOf()
    private val viewTypes: MutableList<ViewType<T>> = mutableListOf()

    private var layoutInflater: LayoutInflater? = null

    fun <A : BaseRecyclerAdapter<T>> register(layout: Int, func: ViewType<T>.() -> Unit): A {
        val viewType = ViewType<T>(layout).apply(func)
        return register(viewType)
    }

    @Suppress("UNCHECKED_CAST")
    fun <A : BaseRecyclerAdapter<T>> register(viewType: ViewType<T>): A {
        viewTypes.add(viewType)
        return this as A
    }

    fun setList(items: List<T>) {
        val listSize = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, listSize)
        addAll(items)
    }

    fun addAll(newItems: List<T>) {
        val start = itemCount
        this.items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(getLayoutInflater(parent).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val type = getItemViewType(position)
        val viewType = viewTypes
                .find { it.layout == type } ?: return
        val data = if (items.size > position) items[position] else null
        viewType.bind?.invoke(position, data, holder.itemView)
        if (viewType.click != null)
            holder.itemView.setOnClickListener {
                viewType.click?.invoke(position, items[position], it)
            }
    }

    override fun getItemViewType(position: Int): Int {
        val viewType = viewTypes
                .find { it.rule.invoke(position, items) }
        return viewType?.layout ?: throw IllegalStateException("Any rule was accepted")
    }

    private fun getLayoutInflater(parent: ViewGroup): LayoutInflater {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        return layoutInflater!!
    }

    open fun saveInstanceState(): Bundle {
        val bundle = Bundle()
//        bundle.putParcelableArrayList(STATE_LIST, ArrayList<Parcelable>(items))
        return bundle
    }

    open fun restoreInstanceState(bundle: Bundle?) {
//        if (bundle == null) return
//        items.addAll(bundle.getParcelableArrayList(STATE_LIST))
    }

}

class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)
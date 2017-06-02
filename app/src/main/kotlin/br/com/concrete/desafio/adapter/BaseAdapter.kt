package br.com.concrete.desafio.adapter

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BaseAdapter<T : Parcelable>(val layoutItem: Int) : RecyclerView.Adapter<BaseViewHolder>() {

    private val STATE_LIST = "STATE_LIST"

    private val items: MutableList<T> = mutableListOf()

    private var layoutInflater: LayoutInflater? = null
    private var click: ((T, Int, View) -> Unit)? = null
    private var binder: ((T, Int, View) -> Unit)? = null

    infix fun click(func: (T, Int, View) -> Unit): BaseAdapter<T> {
        click = func
        return this
    }

    infix fun binder(func: (T, Int, View) -> Unit): BaseAdapter<T> {
        binder = func
        return this
    }

    fun setList(items: List<T>) {
        val listSize = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, listSize)
        this.items.addAll(items)
        notifyItemRangeInserted(0, this.items.size)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder {
        return BaseViewHolder(getLayoutInflater(parent).inflate(layoutItem, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        binder?.invoke(items[position], position, holder.itemView)
        holder.itemView.setOnClickListener { click?.invoke(items[position], position, it) }
    }

    private fun getLayoutInflater(parent: ViewGroup): LayoutInflater {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        return layoutInflater!!
    }

    fun saveInstanceState(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(STATE_LIST, ArrayList<Parcelable>(items))
        return bundle
    }

    fun restoreInstanceState(bundle: Bundle?) {
        if (bundle == null) return
        items.addAll(bundle.getParcelableArrayList(STATE_LIST))
    }

}
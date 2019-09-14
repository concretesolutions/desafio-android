package com.silvioapps.githubkotlin.features.list.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.silvioapps.githubkotlin.BR
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener
import com.silvioapps.githubkotlin.features.shared.utils.Utils

class ListAdapter (list_ : MutableList<ListModel>, viewClickListener_ : ViewClickListener) : RecyclerView.Adapter<ListAdapter.BindingViewHolder>() {
    private var list: MutableList<ListModel>

    companion object{
        private lateinit var viewClickListener: ViewClickListener
    }

    init{
        this.list = list_
        viewClickListener = viewClickListener_
    }

    class BindingViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var viewDataBinding : ViewDataBinding? = null

        init{
            viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(view)
            Utils.setClickListeners(view, viewClickListener, listOf<ListModel>())
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int) : BindingViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_layout, parent, false)
        return BindingViewHolder(view)
    }

    override fun onBindViewHolder(holder : BindingViewHolder, position : Int) {
        Utils.setTags(position, holder.itemView)

        if(list.size > position) {
            val listModel : ListModel = list.get(position)
            holder.viewDataBinding?.setVariable(BR.listModel, listModel)
            holder.viewDataBinding?.executePendingBindings()
        }
    }

    override fun getItemCount() : Int{
        return list.size
    }
}

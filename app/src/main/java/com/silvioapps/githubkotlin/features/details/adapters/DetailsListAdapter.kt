package com.silvioapps.githubkotlin.features.details.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.silvioapps.githubkotlin.BR
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.list.models.ListModel
import kotlin.collections.List

class DetailsListAdapter(list_ : List<ListModel>) : RecyclerView.Adapter<DetailsListAdapter.BindingViewHolder>() {
    private var list = listOf<ListModel>()

    init{
        this.list = list_
    }

    class BindingViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var viewDataBinding : ViewDataBinding? = null

        init{
            viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(view)
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int) : BindingViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_list_layout, parent, false)
        return BindingViewHolder(view)
    }

    override fun onBindViewHolder(holder : BindingViewHolder, position : Int) {
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

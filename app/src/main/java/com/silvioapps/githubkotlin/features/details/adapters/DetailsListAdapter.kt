package com.silvioapps.githubkotlin.features.details.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvioapps.githubkotlin.BR
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.details.fragments.DetailsFragment
import com.silvioapps.githubkotlin.features.details.models.DetailsModel
import com.silvioapps.githubkotlin.features.shared.utils.Utils
import javax.inject.Inject

class DetailsListAdapter @Inject constructor(list_ : MutableList<DetailsModel>, viewClickListener_ : DetailsFragment.DetailsViewClickListener) : RecyclerView.Adapter<DetailsListAdapter.BindingViewHolder>() {
    private var list: MutableList<DetailsModel>

    companion object{
        private lateinit var viewClickListener : DetailsFragment.DetailsViewClickListener
    }

    init{
        list = list_
        viewClickListener = viewClickListener_
    }

    class BindingViewHolder(view : View, list: MutableList<DetailsModel>) : RecyclerView.ViewHolder(view){
        var viewDataBinding : ViewDataBinding? = null

        init{
            viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(view)
            Utils.setClickListeners(view, viewClickListener, list)
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int) : BindingViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_list_layout, parent, false)
        return BindingViewHolder(view, list)
    }

    override fun onBindViewHolder(holder : BindingViewHolder, position : Int) {
        Utils.setTags(position, holder.itemView)

        if(list.size > position) {
            val detailsModel : DetailsModel = list.get(position)
            holder.viewDataBinding?.setVariable(BR.detailsModel, detailsModel)
            holder.viewDataBinding?.executePendingBindings()
        }
    }

    override fun getItemCount() : Int{
        return list.size
    }

    fun getList(): MutableList<DetailsModel>{
        return list
    }
}

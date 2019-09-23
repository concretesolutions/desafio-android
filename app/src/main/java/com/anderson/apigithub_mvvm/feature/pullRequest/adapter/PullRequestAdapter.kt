package com.anderson.apigithub_mvvm.feature.pullRequest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.anderson.apigithub_mvvm.R
import com.anderson.apigithub_mvvm.data.presentation.PullRequestPresentation
import com.anderson.apigithub_mvvm.databinding.ItemPullRequestBinding

/**
 * Created by anderson on 22/09/19.
 */
class PullRequestAdapter constructor(
    var list: ArrayList<PullRequestPresentation>
) : BaseAdapter(){

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemPullRequestBinding
        var view  = LayoutInflater.from(parent?.context).inflate(R.layout.item_pull_request, null)

        binding = DataBindingUtil.bind(view)!!
        binding.item = this.list[position]

        return  binding.root
    }
}
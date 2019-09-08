package com.silvioapps.githubkotlin.features.list.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.silvioapps.githubkotlin.statics.Statics
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.constants.Constants
import com.silvioapps.githubkotlin.databinding.FragmentMainBinding
import com.silvioapps.githubkotlin.features.list.adapters.ListAdapter
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.details.activities.DetailsActivity
import com.silvioapps.githubkotlin.features.list.models.ResponseModel
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener
import com.silvioapps.githubkotlin.features.shared.services.ServiceGenerator
import com.silvioapps.githubkotlin.features.list.services.ListService
import com.silvioapps.githubkotlin.features.shared.fragments.CustomFragment

import java.io.Serializable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : CustomFragment(), ViewClickListener {
    private var list = mutableListOf<ListModel>()
    private var listAdapter : ListAdapter? = null
    private var fragmentMainBinding : FragmentMainBinding? = null
    private var page : Int = 1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(layoutInflater : LayoutInflater, viewGroup : ViewGroup?, bundle : Bundle?) : View?{
        fragmentMainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, viewGroup, false)
        fragmentMainBinding?.progressBar?.setVisibility(View.VISIBLE)

        listAdapter = ListAdapter(list, this)

        if (activity != null) {
            val linearLayoutManager = LinearLayoutManager(activity)
            fragmentMainBinding?.recyclerView?.layoutManager = linearLayoutManager
            fragmentMainBinding?.recyclerView?.itemAnimator = DefaultItemAnimator()
            fragmentMainBinding?.recyclerView?.setHasFixedSize(true)
            fragmentMainBinding?.recyclerView?.adapter = listAdapter
            fragmentMainBinding?.recyclerView?.setOnTouchListener(object: View.OnTouchListener {
                override fun onTouch(v : View , event : MotionEvent) : Boolean{
                    if (!Statics.loadMore && !fragmentMainBinding?.recyclerView?.canScrollVertically(1)!! && event.getAction() == MotionEvent.ACTION_UP) {
                        Statics.loadMore = true

                        showLoading()

                        loadMore()
                    }
                    return false
                }
            })
        }

        if(bundle != null){
            @Suppress("UNCHECKED_CAST")
            val list : MutableList<ListModel> = bundle.getSerializable("list") as MutableList<ListModel>
            setList(list)
            page = bundle.getInt("page")
        }
        else{
            loadMore()
        }

        return fragmentMainBinding?.root
    }

    override fun onSaveInstanceState(outState : Bundle) {
        outState.putSerializable("list", list as Serializable)
        outState.putInt("page", page)
    }

    override fun onClick(context : Context, view : View, position : Int) {
        val intent = Intent(activity, DetailsActivity::class.java)

        val bundle = Bundle()
        bundle.putSerializable("details", list[position] as Serializable)

        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    protected fun loadMore(){
        val service : ListService = ServiceGenerator.createService(Constants.API_BASE_URL, Constants.TIMEOUT, ListService::class.java)
        val call : Call<ResponseModel> = service.getList(Constants.QUERY, Constants.SORT, page)
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call : Call<ResponseModel>, response : Response<ResponseModel>) {
                setList(response.body()?.items!!)
                page++
            }

            override fun onFailure(call : Call<ResponseModel>, t : Throwable) {
                Toast.makeText(activity, getString(R.string.list_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    protected fun setList(values : MutableList<ListModel>){
        hideLoading()

        val startRange = list.size
        list.addAll(values)
        listAdapter?.notifyItemRangeInserted(startRange, values.size)

        fragmentMainBinding?.progressBar?.setVisibility(View.GONE)

        Statics.loadMore = false
    }

    protected fun showLoading(){
        val listModel = ListModel()
        listModel.showLoading = true
        list.add(listModel)
        listAdapter?.notifyItemInserted(list.size - 1)
    }

    protected fun hideLoading(){
        if(Statics.loadMore && list.size >= 1) {
            val index = list.size - 1
            list.removeAt(index)
            listAdapter?.notifyItemRemoved(index)
        }
    }
}

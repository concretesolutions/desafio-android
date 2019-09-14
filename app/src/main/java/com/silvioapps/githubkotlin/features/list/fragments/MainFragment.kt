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
import dagger.android.support.AndroidSupportInjection

import java.io.Serializable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainFragment @Inject constructor(): CustomFragment() {
    @Inject lateinit var listAdapter: ListAdapter
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private var page: Int = 1
    @Inject lateinit var context_: Context
    @Inject lateinit var linearLayoutManager: LinearLayoutManager
    @Inject lateinit var defaultItemAnimator: DefaultItemAnimator

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(layoutInflater : LayoutInflater, viewGroup : ViewGroup?, bundle : Bundle?) : View?{
        fragmentMainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, viewGroup, false)
        fragmentMainBinding.progressBar.setVisibility(View.VISIBLE)

        fragmentMainBinding.recyclerView.layoutManager = linearLayoutManager
        fragmentMainBinding.recyclerView.itemAnimator = defaultItemAnimator
        fragmentMainBinding.recyclerView.setHasFixedSize(true)
        fragmentMainBinding.recyclerView.adapter = listAdapter
        fragmentMainBinding.recyclerView.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v : View , event : MotionEvent) : Boolean{
                if (!Statics.loadMore && !fragmentMainBinding.recyclerView.canScrollVertically(1) && event.getAction() == MotionEvent.ACTION_UP) {
                    Statics.loadMore = true

                    showLoading()

                    loadMore()
                }
                return false
            }
        })

        if(bundle != null){
            @Suppress("UNCHECKED_CAST")
            setList(bundle.getSerializable("list") as MutableList<ListModel>)
            page = bundle.getInt("page")
        }
        else{
            loadMore()
        }

        return fragmentMainBinding.root
    }

    override fun onAttach(context: Context){
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState : Bundle) {
        outState.putSerializable("list", listAdapter.getList() as Serializable)
        outState.putInt("page", page)
    }

    interface ListViewClickListener : ViewClickListener{
        override fun onClick(context : Context, view : View, position : Int, list: List<Any>)

        class ListViewClickListenerImpl @Inject constructor(): ListViewClickListener{
            override fun onClick(context: Context, view: View, position: Int, list: List<Any>) {
                val intent = Intent(context, DetailsActivity::class.java)

                val bundle = Bundle()
                bundle.putSerializable("details", list[position] as Serializable)

                intent.putExtra("data", bundle)
                context.startActivity(intent)
            }
        }
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
                Toast.makeText(context_, getString(R.string.list_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    protected fun setList(values : MutableList<ListModel>){
        hideLoading()

        val startRange = listAdapter.getList().size
        listAdapter.getList().addAll(values)
        listAdapter.notifyItemRangeInserted(startRange, values.size)

        fragmentMainBinding.progressBar.setVisibility(View.GONE)

        Statics.loadMore = false
    }

    protected fun showLoading(){
        val listModel = ListModel()
        listModel.showLoading = true
        listAdapter.getList().add(listModel)
        listAdapter.notifyItemInserted(listAdapter.getList().size - 1)
    }

    protected fun hideLoading(){
        if(Statics.loadMore && listAdapter.getList().size >= 1) {
            val index = listAdapter.getList().size - 1
            listAdapter.getList().removeAt(index)
            listAdapter.notifyItemRemoved(index)
        }
    }
}

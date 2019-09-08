package com.silvioapps.githubkotlin.features.details.fragments

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.constants.Constants
import com.silvioapps.githubkotlin.databinding.FragmentDetailsBinding
import com.silvioapps.githubkotlin.features.details.adapters.DetailsListAdapter
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.list.models.ResponseModel
import com.silvioapps.githubkotlin.features.list.services.ListService
import com.silvioapps.githubkotlin.features.shared.fragments.CustomFragment
import com.silvioapps.githubkotlin.features.shared.services.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class DetailsFragment : CustomFragment(){
    private var fragmentDetailsBinding : FragmentDetailsBinding? = null
    private var list = mutableListOf<ListModel>()
    private var listAdapter : DetailsListAdapter? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(layoutInflater : LayoutInflater, viewGroup : ViewGroup?, bundle : Bundle?) : View? {
        fragmentDetailsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, viewGroup, false)
        fragmentDetailsBinding?.progressBar?.setVisibility(View.VISIBLE)

        listAdapter = DetailsListAdapter(list)

        val linearLayoutManager = LinearLayoutManager(activity)
        fragmentDetailsBinding?.recyclerView?.layoutManager = linearLayoutManager
        fragmentDetailsBinding?.recyclerView?.itemAnimator = DefaultItemAnimator()
        fragmentDetailsBinding?.recyclerView?.setHasFixedSize(true)
        fragmentDetailsBinding?.recyclerView?.adapter = listAdapter

        if(bundle != null){
            @Suppress("UNCHECKED_CAST")
            val list : MutableList<ListModel> = bundle.getSerializable("list") as MutableList<ListModel>
            setList(list)
        }
        else{
            val listModel = arguments?.getSerializable("details") as ListModel
            loadList(listModel)
        }

        return fragmentDetailsBinding?.root
    }

    override fun onSaveInstanceState(outState : Bundle) {
        outState.putSerializable("list", list as Serializable)
    }

    protected fun loadList(listModel : ListModel){
        //todo

        val service : ListService = ServiceGenerator.createService(Constants.API_BASE_URL, Constants.TIMEOUT, ListService::class.java)
        val call : Call<ResponseModel> = service.getList(Constants.QUERY, Constants.SORT, 1)
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call : Call<ResponseModel>, response : Response<ResponseModel>) {
                setList(response.body()?.items!!)
            }

            override fun onFailure(call : Call<ResponseModel>, t : Throwable) {
                Toast.makeText(activity, getString(R.string.list_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    protected fun setList(values : MutableList<ListModel>){
        val startRange = list.size
        list.addAll(values)
        listAdapter?.notifyItemRangeInserted(startRange, values.size)

        fragmentDetailsBinding?.progressBar?.setVisibility(View.GONE)
    }
}

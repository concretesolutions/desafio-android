package com.silvioapps.githubkotlin.features.details.fragments

import android.content.Context
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
import com.silvioapps.githubkotlin.features.details.models.DetailsModel
import com.silvioapps.githubkotlin.features.details.services.DetailsService
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.shared.fragments.CustomFragment
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener
import com.silvioapps.githubkotlin.features.shared.services.ServiceGenerator
import com.silvioapps.githubkotlin.features.shared.utils.Utils
import dagger.android.support.AndroidSupportInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import javax.inject.Inject

class DetailsFragment: CustomFragment(), ViewClickListener {
    private lateinit var fragmentDetailsBinding : FragmentDetailsBinding
    private var list = mutableListOf<DetailsModel>()
    private lateinit var listAdapter : DetailsListAdapter
    private lateinit var listModel : ListModel
    @Inject lateinit var context_: Context

    override fun onCreateView(layoutInflater : LayoutInflater, viewGroup : ViewGroup?, bundle : Bundle?) : View? {
        fragmentDetailsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, viewGroup, false)
        fragmentDetailsBinding.progressBar.setVisibility(View.VISIBLE)

        listAdapter = DetailsListAdapter(list, this)

        val linearLayoutManager = LinearLayoutManager(context_)
        fragmentDetailsBinding.recyclerView.layoutManager = linearLayoutManager
        fragmentDetailsBinding.recyclerView.itemAnimator = DefaultItemAnimator()
        fragmentDetailsBinding.recyclerView.setHasFixedSize(true)
        fragmentDetailsBinding.recyclerView.adapter = listAdapter

        if(bundle != null){
            listModel = bundle.getSerializable("listModel") as ListModel
            @Suppress("UNCHECKED_CAST")
            setList(bundle.getSerializable("list") as MutableList<DetailsModel>)
        }
        else{
            listModel = arguments?.getSerializable("details") as ListModel
            loadList(listModel)
        }

        showBackButton(null, listModel.name!!)

        return fragmentDetailsBinding.root
    }

    override fun onAttach(context: Context){
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState : Bundle) {
        outState.putSerializable("list", list as Serializable)
        outState.putSerializable("listModel", listModel as Serializable)
    }

    override fun onClick(context : Context, view : View, position : Int) {
        Utils.openUrl(context, list[position].html_url!!)
    }

    protected fun loadList(listModel : ListModel){
        val service : DetailsService = ServiceGenerator.createService(Constants.API_BASE_URL, Constants.TIMEOUT, DetailsService::class.java)
        val call : Call<List<DetailsModel>> = service.getList(listModel.owner?.login!!, listModel.name!!)
        call.enqueue(object : Callback<List<DetailsModel>> {
            override fun onResponse(call : Call<List<DetailsModel>>, response : Response<List<DetailsModel>>) {
                setList(response.body()!!)
            }

            override fun onFailure(call : Call<List<DetailsModel>>, t : Throwable) {
                Toast.makeText(context_, getString(R.string.list_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    protected fun setList(values : List<DetailsModel>){
        val startRange = list.size
        list.addAll(values)
        listAdapter.notifyItemRangeInserted(startRange, values.size)

        fragmentDetailsBinding.progressBar.setVisibility(View.GONE)
    }
}

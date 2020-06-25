package com.germanofilho.home.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.germanofilho.core.helper.observeResource
import com.germanofilho.core.ui.BaseFragment
import com.germanofilho.home.R
import com.germanofilho.home.presentation.ui.adapter.RepositoryAdapter
import com.germanofilho.home.presentation.viewmodel.HomeViewModel
import com.germanofilho.home.presentation.viewmodel.HomeViewModelImpl
import com.germanofilho.network.feature.repositorylist.model.entity.Item
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(){

    private val recyclerView by lazy { rv_repository }
    private val adapter by lazy { RepositoryAdapter() }
    private val viewModel by viewModel<HomeViewModelImpl>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.getRepositoryList()
    }

    private fun setupObservers() {
        viewModel.gitRepositoryList.observeResource(this,
            onSuccess = {
                loadData(it.items)
            },
            onError = {
                //showError()
            },
            onLoading = { /*showLoading(it)*/ }
        )
    }

    private fun loadData(repositoryList: List<Item>) {
        adapter.addItem(repositoryList)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
}
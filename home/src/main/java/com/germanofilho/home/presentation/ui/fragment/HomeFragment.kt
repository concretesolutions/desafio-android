package com.germanofilho.home.presentation.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.germanofilho.core.helper.observeResource
import com.germanofilho.core.ui.BaseFragment
import com.germanofilho.home.R
import com.germanofilho.home.di.HomeModule
import com.germanofilho.home.presentation.ui.adapter.RepositoryAdapter
import com.germanofilho.home.presentation.viewmodel.HomeViewModel
import com.germanofilho.network.feature.repositorylist.model.entity.Item
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val repositoryAdapter by lazy { RepositoryAdapter(
        onItemClickListener = {
            val uri = Uri.parse(context?.getString(R.string.deeplink_fork, it))
            findNavController().navigate(uri)
        })}

    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeModule.inject()
        setupRecyclerView()
        setupObservers()

        viewModel.getRepositoryList()
    }

    private fun setupObservers() {
        viewModel.repositoryList.observeResource(this,
            onSuccess = {
                loadData(it.items)
            },
            onError = {
                showError()
            },
            onLoading = {
                showLoading(it)
            }
        )
    }

    private fun loadData(repositoryList: List<Item>) {
        rv_repository.visibility = View.VISIBLE
        repositoryAdapter.addItem(repositoryList)
    }

    private fun showError(){
        error_layout.visibility = View.VISIBLE
        rv_repository.visibility = View.GONE
        showSnackBar(cl_content,
            com.germanofilho.core.R.string.erro_snack,
            com.germanofilho.core.R.string.try_again
        ) { viewModel.getRepositoryList() }
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            progress_bar.visibility = View.VISIBLE
            error_layout.visibility = View.GONE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        with(rv_repository){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = repositoryAdapter
        }
    }
}
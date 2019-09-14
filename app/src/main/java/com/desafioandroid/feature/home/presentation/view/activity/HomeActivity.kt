package com.desafioandroid.feature.home.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.R
import com.desafioandroid.core.helper.observeResource
import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.feature.home.presentation.view.adapter.HomeAdapter
import com.desafioandroid.feature.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private val homeAdapter by lazy {
        HomeAdapter(itemList) {}
    }

    private val itemList = arrayListOf<Item>()
    private val firstListItem: Int = 29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViewModel()
        iniUi()
    }

    private fun iniUi() {
        with(recycler_home) {
            adapter = homeAdapter
            val linearLayoutManager: LinearLayoutManager? = LinearLayoutManager(this@HomeActivity)
            layoutManager = linearLayoutManager
        }
    }

    private fun populateList(itemList: List<Item>) {
        this.itemList.addAll(itemList)
        homeAdapter.notifyItemChanged(itemList.size - firstListItem, itemList.size)
    }

    private fun showSuccess() {
        recycler_home.visibility = View.VISIBLE
    }

    private fun showLoading(isVisibility: Boolean) {
        include_layout_loading.visibility = if (isVisibility) View.VISIBLE else View.GONE
    }

    private fun initViewModel() {
        viewModel.fetchList()
        viewModel.getList.observeResource(this,
            onSuccess = {
                populateList(it)
                showSuccess()
            },
            onLoading = {
                showLoading(it)
            },
            onError = {}
        )
    }
}

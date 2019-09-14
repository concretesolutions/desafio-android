package com.desafioandroid.feature.home.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.R
import com.desafioandroid.core.helper.PaginationScroll
import com.desafioandroid.core.helper.observeResource
import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.feature.home.presentation.view.adapter.HomeAdapter
import com.desafioandroid.feature.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private val homeAdapter by lazy {
        HomeAdapter(itemList) {
            if (releasedLoad){
                Timber.i(it.name)
            }
        }
    }

    private val itemList = arrayListOf<Item>()
    private val firstListItem: Int = 29
    private var releasedLoad: Boolean = true
    private var page: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViewModel()
        iniUi()
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

    private fun iniUi() {
        with(recycler_home) {
            this.adapter = homeAdapter
            val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
            this.addOnScrollListener(object : PaginationScroll(linearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.fetchList(page++)
                    include_layout_loading_bottom.visibility = View.VISIBLE
                    releasedLoad = false
                }

                override fun isLoading(): Boolean {
                    return releasedLoad
                }

                override fun hideMoreItems() {
                    include_layout_loading_center.visibility = View.GONE
                }
            })

            this.layoutManager = linearLayoutManager
        }
    }

    private fun populateList(itemList: List<Item>) {
        this.itemList.addAll(itemList)
        homeAdapter.notifyItemChanged(itemList.size - firstListItem, itemList.size)
    }

    private fun showSuccess() {
        recycler_home.visibility = View.VISIBLE
        include_layout_loading_bottom.visibility = View.GONE
        releasedLoad = true
    }

    private fun showLoading(isVisibility: Boolean) {
        include_layout_loading_center.visibility = if (isVisibility) View.VISIBLE else View.GONE
    }
}

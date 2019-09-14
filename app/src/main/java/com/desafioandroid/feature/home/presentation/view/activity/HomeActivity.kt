package com.desafioandroid.feature.home.presentation.view.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.R
import com.desafioandroid.core.base.BaseActivity
import com.desafioandroid.core.helper.PaginationScroll
import com.desafioandroid.core.helper.observeResource
import com.desafioandroid.core.util.rotationAnimation
import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.feature.home.presentation.view.adapter.HomeAdapter
import com.desafioandroid.feature.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_reload.*
import kotlinx.android.synthetic.main.layout_reload_bottom.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : BaseActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private val homeAdapter by lazy {
        HomeAdapter(itemList) {
            if (releasedLoad) {
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

        setToolbar(title = R.string.title_toolbar_home)

        initViewModel()
        iniUi()
    }

    override fun onStart() {
        super.onStart()
        swipeRefresh()
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
            onError = {
                showError()
            }
        )
    }

    private fun iniUi() {
        with(recycler_home) {
            this.adapter = homeAdapter
            val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
            this.addOnScrollListener(object : PaginationScroll(linearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.fetchList(page++)
                    include_layout_loading_bottom_scroll.visibility = View.VISIBLE
                    releasedLoad = false
                }

                override fun isLoading(): Boolean {
                    return releasedLoad
                }

                override fun hideMoreItems() {
                    include_layout_loading_full_screen.visibility = View.GONE
                }
            })

            this.layoutManager = linearLayoutManager
        }
    }

    private fun populateList(itemList: List<Item>) {
        this.itemList.addAll(itemList)
        homeAdapter.notifyItemChanged(itemList.size - firstListItem, itemList.size)
    }

    private fun clearListAndAdd() {
        page = 2
        homeAdapter.clear(itemList)
        viewModel.fetchList()
    }

    private fun swipeRefresh() {
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                if (releasedLoad) {
                    clearListAndAdd()
                }
                swipe_refresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun showSuccess() {
        recycler_home.visibility = View.VISIBLE
        include_layout_loading_bottom_scroll.visibility = View.GONE
        include_layout_reload_bottom_scroll.visibility = View.GONE
        include_layout_reload_full_screen.visibility = View.GONE
        releasedLoad = true
    }

    private fun showLoading(isVisibility: Boolean) {
        include_layout_loading_full_screen.visibility = if (isVisibility) View.VISIBLE else View.GONE
    }

    private fun showError() {
        if (page > 2) {
            errorBottomScroll()
        } else {
            errorFullScreen()
        }
    }

    private fun errorFullScreen() {
        include_layout_reload_full_screen.visibility = View.VISIBLE
        include_layout_loading_bottom_scroll.visibility = View.GONE
        recycler_home.visibility = View.GONE

        showLoadingAndHideButtonRefreshFullScreen(false)

        image_refresh_full_screen.setOnClickListener { view ->
            view.rotationAnimation()

            clearListAndAdd()

            showLoadingAndHideButtonRefreshFullScreen(true)
            include_layout_loading_full_screen.visibility = View.GONE
        }
    }

    private fun errorBottomScroll() {
        showLoadingAndHideButtonRefreshBottomScroll(false)

        image_refresh_bottom_default.rotationAnimation().setOnClickListener {
            viewModel.fetchList(page - 1)

            showLoadingAndHideButtonRefreshBottomScroll(true)
            include_layout_loading_full_screen.visibility = View.GONE
        }
    }

    private fun showLoadingAndHideButtonRefreshFullScreen(isVisibility: Boolean) {
        progress_loading_full_screen.visibility = if (isVisibility) View.VISIBLE else View.GONE
        image_refresh_full_screen.visibility = if (isVisibility) View.GONE else View.VISIBLE
    }

    private fun showLoadingAndHideButtonRefreshBottomScroll(isVisibility: Boolean) {
        include_layout_loading_bottom_scroll.visibility = if (isVisibility) View.VISIBLE else View.GONE
        include_layout_reload_bottom_scroll.visibility = if (isVisibility) View.GONE else View.VISIBLE
    }
}

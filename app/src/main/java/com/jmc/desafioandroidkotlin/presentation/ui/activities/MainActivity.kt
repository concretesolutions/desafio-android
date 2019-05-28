package com.jmc.desafioandroidkotlin.presentation.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jmc.desafioandroidkotlin.R
import com.jmc.desafioandroidkotlin.domain.usecase.coroutines.Completable
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem
import com.jmc.desafioandroidkotlin.presentation.viewModels.MainViewModel
import com.jmc.desafioandroidkotlin.presentation.ui.adapters.PagedRepositoryAdapter
import com.jmc.desafioandroidkotlin.utils.*

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()


    private val connectivityManager: ConnectivityManager by inject()

    private val repositoryAdapter: PagedRepositoryAdapter by inject {
        parametersOf(RepositoryManager())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshRepository.isEnabled = false

        with(reciclerRepository) {
            layoutManager = LinearLayoutManager(context)
            adapter = repositoryAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        with(viewModel) {
            observe(fetchRepositories, ::fetchObserver)
            observe(pagedRepositories, ::repositoriesObserver)
        }
    }

    private fun repositoriesObserver(result: PagedList<RepositoryItem>?) {
        repositoryAdapter.submitList(result)
    }

    private fun fetchObserver(result: Completable?) {
        when (result) {
            is Completable.OnLoading -> {
                swipeRefreshRepository.isRefreshing = true
            }
            is Completable.OnError -> {
                swipeRefreshRepository.isRefreshing = false

                val errorString = if (connectivityManager.isNetworkAvailable())
                    R.string.toast_error_unexpected
                else
                    R.string.toast_error_network

                longToast(errorString)
            }
            else -> {
                swipeRefreshRepository.isRefreshing = false
            }
        }
    }

    inner class RepositoryManager : PagedRepositoryAdapter.AdapterCallback {
        override fun onRepositoryClicked(item: RepositoryItem) {
            startActivity<PullsActivity>(
                EXTRA_TITLE to item.name,
                EXTRA_NAME to item.fullName
            )
        }

        override fun onUserClicked(item: RepositoryItem) {
            startActivity<BrowserActivity>(
                EXTRA_TITLE to item.userLogin,
                EXTRA_URL to item.userUrl
            )
        }

        override fun loadImage(url: String, imageView: ImageView) {
            if (url.isNotEmpty())
                Glide.with(this@MainActivity).load(url).into(imageView)
            else
                imageView.imageResource = R.mipmap.ic_launcher
        }
    }
}

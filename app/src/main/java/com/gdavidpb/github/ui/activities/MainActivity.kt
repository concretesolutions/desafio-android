package com.gdavidpb.github.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdavidpb.github.R
import com.gdavidpb.github.domain.usecase.coroutines.Completable
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.presentation.viewModels.MainViewModel
import com.gdavidpb.github.ui.adapters.PagedRepositoryAdapter
import com.gdavidpb.github.utils.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val picasso: Picasso by inject()

    private val connectivityManager: ConnectivityManager by inject()

    private val repositoryAdapter: PagedRepositoryAdapter by inject {
        parametersOf(RepositoryManager())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sRefreshRepository.isEnabled = false

        with(rViewRepository) {
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
                sRefreshRepository.isRefreshing = true
            }
            is Completable.OnError -> {
                sRefreshRepository.isRefreshing = false

                val errorString = if (connectivityManager.isNetworkAvailable())
                    R.string.toast_error_unexpected
                else
                    R.string.toast_error_network

                longToast(errorString)
            }
            else -> {
                sRefreshRepository.isRefreshing = false
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
                picasso.load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(CircleTransform())
                    .into(imageView)
            else
                imageView.imageResource = R.mipmap.ic_launcher
        }
    }
}

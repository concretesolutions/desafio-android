package com.jmc.desafioandroidkotlin.presentation.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jmc.desafioandroidkotlin.R
import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.presentation.model.PullItem
import com.jmc.desafioandroidkotlin.presentation.viewModels.PullsViewModel
import com.jmc.desafioandroidkotlin.presentation.ui.adapters.PullAdapter
import com.jmc.desafioandroidkotlin.utils.*
import com.jmc.desafioandroidkotlin.domain.usecase.coroutines.Result
import kotlinx.android.synthetic.main.activity_pulls.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PullsActivity : AppCompatActivity() {

    private val viewModel: PullsViewModel by viewModel()



    private val connectivityManager: ConnectivityManager by inject()

    private val pullsAdapter: PullAdapter by inject {
        parametersOf(PullManager())
    }

    private val extraTitle by lazy {
        intent.getStringExtra(EXTRA_TITLE)
    }

    private val extraName by lazy {
        intent.getStringExtra(EXTRA_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.label_git_hub, extraTitle)

        sRefreshPull.isEnabled = false

        with(rViewPull) {
            layoutManager = LinearLayoutManager(context)
            adapter = pullsAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        with(viewModel) {
            observe(pulls, ::pullsObserver)

            getPulls(repository = extraName)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    private fun pullsObserver(result: Result<List<PullModel>>?) {
        when (result) {
            is Result.OnLoading -> {
                sRefreshPull.isRefreshing = true
            }
            is Result.OnSuccess -> {
                sRefreshPull.isRefreshing = false

                val pulls = result.value.map { it.toPullItem() }

                pullsAdapter.swapItems(new = pulls)

                if (pulls.isNotEmpty()) {
                    tViewPullEmpty.visibility = View.GONE
                    rViewPull.visibility = View.VISIBLE
                } else {
                    rViewPull.visibility = View.GONE
                    tViewPullEmpty.visibility = View.VISIBLE
                }
            }
            is Result.OnError -> {
                sRefreshPull.isRefreshing = false

                val errorString = if (connectivityManager.isNetworkAvailable())
                    R.string.toast_error_unexpected
                else
                    R.string.toast_error_network

                longToast(errorString)
            }
            else -> {
                sRefreshPull.isRefreshing = false
            }
        }
    }

    inner class PullManager : PullAdapter.AdapterCallback {
        override fun onPullClicked(item: PullItem) {
            startActivity<BrowserActivity>(
                EXTRA_TITLE to item.title,
                EXTRA_URL to item.url
            )
        }

        override fun onUserClicked(item: PullItem) {
            startActivity<BrowserActivity>(
                EXTRA_TITLE to item.userLogin,
                EXTRA_URL to item.userUrl
            )
        }

        override fun loadImage(url: String, imageView: ImageView) {
            if (url.isNotEmpty())
                Glide.with(this@PullsActivity).load(url).into(imageView)
            else
                imageView.imageResource = R.mipmap.ic_launcher
        }
    }
}

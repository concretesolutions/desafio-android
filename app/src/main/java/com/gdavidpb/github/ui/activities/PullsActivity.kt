package com.gdavidpb.github.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdavidpb.github.R
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.usecase.coroutines.Result
import com.gdavidpb.github.presentation.model.PullItem
import com.gdavidpb.github.presentation.viewModels.PullsViewModel
import com.gdavidpb.github.ui.adapters.PullAdapter
import com.gdavidpb.github.utils.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pulls.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PullsActivity : AppCompatActivity() {

    private val viewModel: PullsViewModel by viewModel()

    private val picasso: Picasso by inject()

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

    private fun pullsObserver(result: Result<List<Pull>>?) {
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
                picasso.load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(CircleTransform())
                    .into(imageView)
            else
                imageView.imageResource = R.mipmap.ic_launcher
        }
    }
}

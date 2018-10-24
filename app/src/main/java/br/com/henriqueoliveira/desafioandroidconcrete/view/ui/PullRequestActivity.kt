package br.com.henriqueoliveira.desafioandroidconcrete.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import br.com.henriqueoliveira.desafioandroidconcrete.R
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.toast
import br.com.henriqueoliveira.desafioandroidconcrete.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.pull_request_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestActivity : BaseActivity() {

    private var repositoryId: Int = -1
    private val repoViewModel: RepositoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportPostponeEnterTransition()

        repositoryId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        if (repositoryId == -1) {
            toast(getString(R.string.invalid_repositoryId))
            finish()
            return
        }

        val imageTransitionName = intent.getStringExtra(EXTRA_MOVIE_TRANSITION_NAME)
        tvStatus.transitionName = imageTransitionName
        supportStartPostponedEnterTransition() //start the transition animation

        handleViewModel()
    }

    private fun handleViewModel() {
//        moviesVM.getMoviesById(repositoryId)
//        moviesVM.isLoading.observe(this, Observer {
//            detailActivityProgress.show(it)
//        })
//        moviesVM.state.observe(this, Observer {
//            when (it.status) {
//                Resource.RequestStatus.LOADING -> Unit
//                Resource.RequestStatus.SUCCESS -> {
//                    fillMovieData(it.data)
//                }
//                Resource.RequestStatus.ERROR -> {
//                    toast(getString(R.string.str_unable_to_load_movie))
//                }
//            }
//        })
    }


    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_REPOSITORY_NAME = "EXTRA_REPOSITORY_NAME"
        private const val EXTRA_REPOSITORY_OWNER = "EXTRA_REPOSITORY_OWNER"
        private const val EXTRA_MOVIE_TRANSITION_NAME = "EXTRA_MOVIE_TRANSITION_NAME"


        fun startWithAnimation(activity: Activity, repositoryName: String, repositoryOwner: String, sharedImageView: View) {
            val intent = Intent(activity, PullRequestActivity::class.java)
            intent.putExtra(EXTRA_REPOSITORY_NAME, repositoryName)
            intent.putExtra(EXTRA_REPOSITORY_OWNER, repositoryOwner)
            intent.putExtra(EXTRA_MOVIE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView))

            val options = ViewCompat.getTransitionName(sharedImageView)?.let {
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        sharedImageView,
                        it)
            }

            activity.startActivity(intent, options?.toBundle())
        }
    }
}
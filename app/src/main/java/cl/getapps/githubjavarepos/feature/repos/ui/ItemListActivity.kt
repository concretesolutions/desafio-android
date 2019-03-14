package cl.getapps.githubjavarepos.feature.repos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.core.extension.DomainRepo
import cl.getapps.githubjavarepos.feature.repopullrequests.ui.ItemDetailActivity
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of repos, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of repos and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    val reposRecyclerViewAdapter: ReposRecyclerViewAdapter by inject()
    val reposViewModel: ReposViewModel by viewModel()
    val repos: MutableList<DomainRepo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        bindScope(getOrCreateScope("Repos"))

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView()

        reposViewModel.getReposs().observe(this, Observer<MutableList<DomainRepo>> {
            if (it != null) setupRecyclerViewData(it)
        })
        reposViewModel.fetchRepos(ReposParams())
    }

    private fun setupRecyclerViewData(repos: MutableList<DomainRepo>?) {
        reposRecyclerViewAdapter.values = repos
        reposRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        item_list.adapter = reposRecyclerViewAdapter
    }
}

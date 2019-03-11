package cl.getapps.githubjavarepos.feature.repos.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.BuildConfig
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.core.extension.DomainRepo
import cl.getapps.githubjavarepos.feature.repopullrequests.ui.ItemDetailActivity
import cl.getapps.githubjavarepos.feature.repopullrequests.ui.ItemDetailFragment
import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.ReposAPI
import cl.getapps.githubjavarepos.feature.repos.domain.Repo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val reposAPI = retrofit.create(ReposAPI::class.java)

        val reposresponse = reposAPI.repos()

        reposresponse.enqueue(object : Callback<ReposResponse> {
            override fun onFailure(call: Call<ReposResponse>, t: Throwable) {
                println("TERRIBLE ${t.message}")
            }

            override fun onResponse(call: Call<ReposResponse>, response: Response<ReposResponse>) {
                println(response.body())
                setupRecyclerView(item_list, response.body()?.toDomainRepos())
                println("cosas")
            }
        })
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        repos: MutableList<DomainRepo>?
    ) {
        recyclerView.adapter =
            SimpleItemRecyclerViewAdapter(
                this,
                repos,
                twoPane
            )
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<Repo>?,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Repo
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemDetailFragment.ARG_ITEM_ID, item.name)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item.name)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values?.get(position)
            holder.idView.text = item?.name
            holder.contentView.text = item?.description

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values?.size ?: 0

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}

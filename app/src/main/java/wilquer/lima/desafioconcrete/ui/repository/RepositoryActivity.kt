package wilquer.lima.desafioconcrete.ui.repository

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repository_activity.*
import org.jetbrains.anko.toast
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.Repository
import wilquer.lima.desafioconcrete.ui.pullrequest.PullRequestActivity
import wilquer.lima.desafioconcrete.util.Constants
import wilquer.lima.desafioconcrete.util.RecyclerClickListener
import wilquer.lima.desafioconcrete.util.adapter.RecyclerRepositoryAdapter

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View, RecyclerClickListener {

    private var presenter: RepositoryContract.Presenter = RepositoryPresenter(this)
    private var listRepositories = mutableListOf<Repository>()
    private var countPages = 1
    private val adapterRepository = RecyclerRepositoryAdapter(listRepositories, this@RepositoryActivity, this@RepositoryActivity)
    private var isLoading = false
    //private lateinit var endlessScrollListener: EndlessScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_activity)

        window.navigationBarColor = Color.BLACK
        presenter.initView(countPages)


        recycleRepositories.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RepositoryActivity, RecyclerView.VERTICAL, false)
            adapter = adapterRepository
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = linearLayoutManager.itemCount
                    val lastVisible = linearLayoutManager.findLastVisibleItemPosition()
                    if (!isLoading && totalItemCount <= lastVisible + 15) {
                        isLoading = true
                        (presenter as RepositoryPresenter).getRepositories(++countPages)
                    }
                }
            })
        }

        /*if(savedInstanceState != null){
            savedInstanceState.run {
                getSerializable(Constants.SAVE_REPOSITORIES).run {
                    listRepositories.clear()
                    listRepositories.addAll(this as ArrayList<Repository>)
                }
            }
            if(savedInstanceState.getSerializable(Constants.SAVE_REPOSITORIES) is MutableList<*>) {
                listRepositories.addAll(savedInstanceState.getSerializable(Constants.SAVE_REPOSITORIES))
            }
        }*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putSerializable(Constants.SAVE_REPOSITORIES,listRepositories)
    }

    override fun setProgress(active: Boolean) {
        if (active) {
            progress?.visibility = View.VISIBLE
        } else {
            progress?.visibility = View.INVISIBLE
        }
    }

    override fun repositories(listRepositories: List<Repository>?) {
        if (countPages > 1) {
            //toast(countPages.toString())
            val lastPosition = this.listRepositories.size
            this.listRepositories.addAll(listRepositories!!)
            recycleRepositories.adapter?.notifyItemRangeInserted(lastPosition, listRepositories.size)
        } else {
            this.listRepositories.addAll(listRepositories!!)
            recycleRepositories.adapter?.notifyDataSetChanged()
        }
        isLoading = false
    }

    override fun error(msg: String) {
        toast(msg)
    }

    override fun onRecyclerClickListener(position: Int) {
        val intent = Intent(this@RepositoryActivity, PullRequestActivity::class.java)
        intent.putExtra(Constants.REPOSITORY_NAME, listRepositories[position].name)
        intent.putExtra(Constants.CREATOR, listRepositories[position].owner.login)
        startActivity(intent)
    }
}
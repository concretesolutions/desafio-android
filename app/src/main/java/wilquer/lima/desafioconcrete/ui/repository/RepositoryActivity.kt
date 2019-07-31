package wilquer.lima.desafioconcrete.ui.repository

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.repository_activity.*
import org.jetbrains.anko.toast
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.Repository
import wilquer.lima.desafioconcrete.util.RecyclerRepositoryAdapter

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    private var presenter: RepositoryContract.Presenter? = null
    private var listRepositories = mutableListOf<Repository>()
    private var countPages = 1
    private val linearLayoutManager = LinearLayoutManager(this)
    private var isLoading = false
    val snapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_activity)

        presenter = RepositoryPresenter(this)
        presenter?.initView(countPages)
        recycleRepositories.apply {
            layoutManager = linearLayoutManager
            adapter = RecyclerRepositoryAdapter(listRepositories, this@RepositoryActivity)
        }
        snapHelper.attachToRecyclerView(recycleRepositories)
        recycleRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val snapPosition = snapHelper.getSnapPosition(recyclerView)
                //val visibleItemCount = linearLayoutManager.getPosition(snapView)
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                val lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                Log.d("endlessscroll", "last visible position: $lastVisibleItem, total count: $totalItemCount")
                //if(linearLayoutManager.findLastCompletelyVisibleItemPosition()>= linearLayoutManager.itemCount - 1){
                if (!isLoading && (totalItemCount) <= (firstVisibleItem + 2)) {
                    isLoading = true
                    countPages++
                    presenter?.getRepositories(countPages)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val snapPosition = snapHelper.getSnapPosition(recyclerView)

            }
        })
        window.navigationBarColor = Color.BLACK
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
            val lastPosition = this.listRepositories.size - 1
            this.listRepositories.addAll(listRepositories!!)
            recycleRepositories.adapter?.notifyItemRangeInserted(lastPosition, listRepositories.size)
        } else {
            this.listRepositories.addAll(listRepositories!!)
            recycleRepositories.adapter?.notifyDataSetChanged()
        }
    }

    override fun error(msg: String) {
        toast(msg)
    }

    fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }
}
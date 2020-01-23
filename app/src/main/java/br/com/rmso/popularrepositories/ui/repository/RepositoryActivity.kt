package br.com.rmso.popularrepositories.ui.repository

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rmso.popularrepositories.utils.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.Repository
import br.com.rmso.popularrepositories.ui.pullrequest.PullRequestActivity
import br.com.rmso.popularrepositories.utils.Constants
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity(),
    ListOnClickListener, IRepository.View {
    private var page = 1
    private var repositoriesArrayList = ArrayList<Repository>()
    private var isLoading = false
    private var lastPosition = 0
    private val constants = Constants()
    private var repositoryPresenter: IRepository.Presenter = RepositoryPresenter(this)

    var linearLayoutManager = LinearLayoutManager(this@RepositoryActivity)
    var adapterRepository = RepositoryAdapter(
        repositoriesArrayList,
        this@RepositoryActivity
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        toolbar_main.title = constants.repositories

        if (savedInstanceState != null) {
            repositoriesArrayList.addAll(savedInstanceState.getParcelableArrayList(constants.listRepositoryInstance))
            page = savedInstanceState.getInt(constants.countInstance)
        }else {
            (repositoryPresenter as RepositoryPresenter).requestList(page, lastPosition)
        }

        rv_repository.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = adapterRepository

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastCompleteItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (!isLoading) {
                        if (lastCompleteItem == repositoriesArrayList.size - 1){
                            page += 1
                            isLoading = true
                            lastPosition = repositoriesArrayList.size + 1
                            (repositoryPresenter as RepositoryPresenter).requestList(page, lastPosition)
                        }
                    }
                }
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(constants.listRepositoryInstance, repositoriesArrayList)
        outState.putInt(constants.countInstance, page)
    }

    override fun updateList(list: List<Repository>?, lastPosition: Int) {
        repositoriesArrayList.addAll(list!!)
        if (page > 1) {
            rv_repository.adapter?.notifyItemRangeInserted(lastPosition, repositoriesArrayList.size)
        }else {
            rv_repository.adapter?.notifyDataSetChanged()
        }
        isLoading = false
    }

   override fun onClick(position: Int) {
        val intent = Intent(this@RepositoryActivity, PullRequestActivity::class.java)
        val repository = repositoriesArrayList[position]
        intent.putExtra(constants.owner, repository.owner.login)
        intent.putExtra(constants.repository, repository.name)
        startActivity(intent)
    }

    override fun progressBar(status: Boolean) {
        if(status) {
            pb_loading.visibility = View.VISIBLE
        }else {
            pb_loading.visibility = View.GONE
        }
    }

    override fun errorRequest(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

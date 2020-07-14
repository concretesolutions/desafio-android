package com.bassul.mel.app.feature.repositoriesList.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.*
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.interactor.RepoInteractorImpl
import com.bassul.mel.app.feature.pullRequestList.view.PullRequestActivity
import com.bassul.mel.app.feature.repositoriesList.RepoPresenterImpl
import com.bassul.mel.app.feature.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.feature.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.feature.repositoriesList.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RepositoriesListContract.View {

    private val presenter : RepositoriesListContract.Presenter by lazy {
        RepoPresenterImpl(this)
    }

    private val repository : RepositoriesListContract.Repository by lazy {
        RepoRepositoryImpl(GithubAPI())
    }

    private val interactor : RepositoriesListContract.Interactor by lazy {
        RepoInteractorImpl(presenter, repository)
    }

    private var adapter: RepositoryAdapter? = null
    private var pages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initRepositoriesCard()
    }

   override fun initRecyclerView() {
        arRecyclerViewRepositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(this, mutableListOf(), itemClickListener = itemOnClick)
       arRecyclerViewRepositories.adapter = adapter
        arRecyclerViewRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)

               val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
               if (linearLayoutManager != null && isBottomOfList(linearLayoutManager)) {
                   interactor.loadRepositories(++pages)
               }
           }
       })
    }

    fun isBottomOfList(llm : LinearLayoutManager) : Boolean{
        return llm.findLastCompletelyVisibleItemPosition() == adapter!!.items.size - 1
    }

    override  fun initRepositoriesCard(){
        interactor.loadRepositories(pages)
    }

    override  fun showCard(repositories: ArrayList<Item>) {
        adapter!!.addItems(repositories)
        arProgressbar.visibility = View.GONE
    }

    override fun showErrorCard(errorMessage: Int) {
        createAndShowAlertDialog(errorMessage)
    }

     fun showErrorPullRequestCard(errorPullRequest: Int) {
        createAndShowAlertDialog(errorPullRequest)
        arProgressbar?.visibility = View.GONE
        arRecyclerViewRepositories?.visibility = View.VISIBLE
    }

    private fun createAndShowAlertDialog(message : Int){
        val mAlertDialog = AlertDialog.Builder(this)
        mAlertDialog.setTitle("Erro")
        mAlertDialog.setMessage(getString(message))
        mAlertDialog.show()
    }

    val itemOnClick : (Item) -> Unit = {item ->
        /*interactor.getSelectedItem(item)
        arProgressbar.visibility = View.VISIBLE
        arRecyclerViewRepositories.visibility = View.GONE*/

        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("login", item.owner.login)
        intent.putExtra("nameRepository", item.name)
        startActivity(intent)
    }

     fun openActivityPullRequest(pullRequest: ArrayList<PullRequest>) {
        /*val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("pullRequest", pullRequest)
        startActivity(intent)*/
    }

    override fun onStop() {
        super.onStop()
        arProgressbar?.visibility = View.GONE
        arRecyclerViewRepositories?.visibility = View.VISIBLE
    }
}

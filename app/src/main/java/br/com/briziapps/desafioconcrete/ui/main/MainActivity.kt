package br.com.briziapps.desafioconcrete.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.briziapps.desafioconcrete.R
import br.com.briziapps.desafioconcrete.adapters.AdapterRecyclerRepos
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoObjects
import br.com.briziapps.desafioconcrete.mvp.Mvp
import br.com.briziapps.desafioconcrete.ui.repo_pulls.RepoPullsActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Mvp.ViewMain {

    //private val tag = "MainActivity"

    private var languageSelected = "Java"
    private var pageSelected = 1
    private lateinit var presenterMain: Mvp.PresenterMain

    private lateinit var rVRepos:RecyclerView
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var lastVisibleItem: Int = 0
    private lateinit var adapterRecyclerRepos:AdapterRecyclerRepos
    private var repositoriesList:MutableList<GitHubRepoObjects>? = null

    private lateinit var progressBar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        presenterMain = PresenterMain(this)
        presenterMain.getContext(this)
        presenterMain.getReposOnApi(languageSelected, pageSelected.toString())


    }

    private fun initViews(){

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.toolbar_title)
        supportActionBar?.subtitle = getString(R.string.toolbar_sub_title, languageSelected)


        val navigationView = nav_view
        navigationView.setNavigationItemSelectedListener(this)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        progressBar = pBLoadRepos

        repositoriesList = ArrayList()

        rVRepos = rVRepositories
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rVRepos.layoutManager = linearLayoutManager
        rVRepos.setHasFixedSize(true)
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
        setRecyclerViewScrollListener()
        adapterRecyclerRepos = AdapterRecyclerRepos(repositoriesList as ArrayList<GitHubRepoObjects>, this::recyclerRepoclick)
        rVRepos.adapter = adapterRecyclerRepos

    }

    override fun updateRecyclerViewRepos(repositories: List<GitHubRepoObjects>) {
        //Log.d(tag, "Checking list = repo 0 name = ${repositories[0].repositorieName} owner avatar = ${repositories[0].owner?.ownerAvatar}")
        repositoriesList?.addAll(repositories)
        adapterRecyclerRepos.notifyDataSetChanged()
        supportActionBar?.subtitle = getString(R.string.toolbar_sub_title, languageSelected)
        hideProgressBar()
        rVRepos.addOnScrollListener(scrollListener)
    }

    private fun recyclerRepoclick( repositorieName: String, pullRequestsUrl:String){
        //Log.d(tag, "Click on recycleView pull request url = $pullRequestsUrl")
        val intentShowRepoPulls = Intent(this, RepoPullsActivity::class.java)
        intentShowRepoPulls.putExtra("repositorieName", repositorieName)
        intentShowRepoPulls.putExtra("pullRequestsUrl", pullRequestsUrl)
        startActivity(intentShowRepoPulls)
        overridePendingTransition(R.animator.slide_in_rigth, R.animator.slid_out_left)
    }

    private fun setRecyclerViewScrollListener(){
        scrollListener = object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                //Log.d("MainActivity", "totalItemCount = $totalItemCount lastVisibleItem = ${linearLayoutManager.findLastVisibleItemPosition()}")
                if (totalItemCount == linearLayoutManager.findLastVisibleItemPosition() + 1){
                    rVRepos.removeOnScrollListener(scrollListener)
                    progressBar.visibility = View.VISIBLE
                    pageSelected ++
                    presenterMain.getReposOnApi(languageSelected, pageSelected.toString())
                    Log.d("MainActivity", "Load new list pageSelected = $pageSelected")
                }
            }
        }
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onNavigationItemSelected(item: MenuItem) :Boolean{

        when (item.itemId) {

            R.id.java -> {
                if ( repositoriesList != null && repositoriesList!!.isNotEmpty() && languageSelected != "Java"){
                    progressBar.visibility = View.VISIBLE
                    repositoriesList?.clear()
                    rVRepos.removeOnScrollListener(scrollListener)
                    adapterRecyclerRepos.notifyDataSetChanged()
                    languageSelected = "Java"
                    pageSelected = 1
                    presenterMain.getReposOnApi(languageSelected, pageSelected.toString())
                }
            }
            R.id.kotlin -> {
                if ( repositoriesList != null && repositoriesList!!.isNotEmpty() && languageSelected != "Kotlin"){
                    progressBar.visibility = View.VISIBLE
                    repositoriesList?.clear()
                    rVRepos.removeOnScrollListener(scrollListener)
                    adapterRecyclerRepos.notifyDataSetChanged()
                    languageSelected = "Kotlin"
                    pageSelected = 1
                    presenterMain.getReposOnApi(languageSelected, pageSelected.toString())
                }
            }
            R.id.c_sharp ->{
                if ( repositoriesList != null && repositoriesList!!.isNotEmpty() && languageSelected != "C#"){
                    progressBar.visibility = View.VISIBLE
                    repositoriesList?.clear()
                    rVRepos.removeOnScrollListener(scrollListener)
                    adapterRecyclerRepos.notifyDataSetChanged()
                    languageSelected = "C#"
                    pageSelected = 1
                    presenterMain.getReposOnApi(languageSelected, pageSelected.toString())
                }
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }

}

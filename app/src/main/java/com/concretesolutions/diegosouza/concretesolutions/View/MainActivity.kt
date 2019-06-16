package com.concretesolutions.diegosouza.concretesolutions.View

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.concretesolutions.diegosouza.concretesolutions.Adapter.ListaAdapter
import com.concretesolutions.diegosouza.concretesolutions.Api.RetrofitClient
import com.concretesolutions.diegosouza.concretesolutions.Model.ItemsLista
import com.concretesolutions.diegosouza.concretesolutions.Model.Lista
import com.concretesolutions.diegosouza.concretesolutions.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: ListaAdapter

    var offset = 1
    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        progressBar.visibility = View.VISIBLE
        carregarLista(offset)

        title = "Home"

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_help -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.drawer_exit -> {
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun carregarLista(offset: Int) {
        val call = RetrofitClient().listaService().lista(offset)
        call.enqueue(object : Callback<Lista> {
            override fun onResponse(
                call: Call<Lista>?,
                response: Response<Lista>?
            ) {
                response?.body()?.let {
                    loading = false
                    val lista: Lista = it
                    configurarListagem(lista.items)
                }
            }

            override fun onFailure(call: Call<Lista>?, t: Throwable?) {
                loading = false
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun addLista(offset: Int) {

        loading = true

        val call = RetrofitClient().listaService().lista(offset)
        call.enqueue(object : Callback<Lista> {
            override fun onResponse(
                call: Call<Lista>?,
                response: Response<Lista>?
            ) {
                loading = false
                response?.body()?.let {
                    adapter.addData(it.items)
                }
            }

            override fun onFailure(call: Call<Lista>?, t: Throwable?) {
                loading = false
            }
        })
    }

    private fun configurarListagem(lista: List<ItemsLista>) {

        val recyclerView = recyclerView_list
        adapter = ListaAdapter(this)

        recyclerView.adapter = adapter

        adapter.setList(lista as ArrayList<ItemsLista>)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        progressBar.visibility = View.GONE

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (!recyclerView.canScrollVertically(1)) {

                    if (!loading) {
                        offset += 1
                        addLista(offset)
                    }
                }
            }
        })

        adapter.setOnItemClickListener(object : ListaAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                val intent = Intent(this@MainActivity, InformationActivity::class.java);
                intent.putExtra("full_name_pulls", lista[pos].full_name)
                startActivity(intent);
            }
        })

    }
}

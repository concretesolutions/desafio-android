package br.com.eriberto.desafioandroidconcrete.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.eriberto.desafioandroidconcrete.R
import br.com.eriberto.desafioandroidconcrete.model.RepositorioModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.InteracaoComLista
import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchModel
import br.com.eriberto.desafioandroidconcrete.model.interfaces.RepositorioSearchView
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.RepositorioDAO
import br.com.eriberto.desafioandroidconcrete.presenter.RepositorioSearchPresenter
import br.com.eriberto.desafioandroidconcrete.view.recyclerViewAdapter.AdapterListaRepository
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, RepositorioSearchView {

    private val presenter = RepositorioSearchPresenter(view = this, model = RepositorioModel)
    private var novaLista: Boolean = true
    private lateinit var listaAtual: ArrayList<Repositorio>
    private var numPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


        if (recyclerViewRepositorios is RecyclerView)
            with(recyclerViewRepositorios) {
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        swipeRefresh_repositorios.setOnRefreshListener {
            presenter.search(page = 1)
        }


        presenter.search(page = 0)

    }

    /*override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putSerializable("listaAtual", listaAtual)
        outState?.putSerializable("numPage", numPage)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            mostarLista(savedInstanceState.getSerializable("listaAtual") as ArrayList<Repositorio>)
            val adapter = recyclerViewRepositorios.adapter as AdapterListaRepository

            adapter.setNumeroDaPaginaAtual(savedInstanceState.getInt("numPage"))
        }

    }*/

    override fun showProgress() {
        swipeRefresh_repositorios.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefresh_repositorios.isRefreshing = false
    }


    override fun showResult(result: RepositorioDAO) {
        Toast.makeText(this, result.items.size.toString(), Toast.LENGTH_LONG).show()

        if (novaLista) {
            listaAtual = result.items

            mostarLista(result.items)

        } else {
            val adapter = recyclerViewRepositorios.adapter as AdapterListaRepository
            adapter.setLista(result.items)
            adapter.setNumeroDaPaginaAtual(numPage)
            listaAtual = adapter.getList()
        }

    }

    private fun mostarLista(items: ArrayList<Repositorio>) {
        recyclerViewRepositorios.adapter = AdapterListaRepository(
            context = this,
            mValues = items,
            interacaoComLista = object : InteracaoComLista {
                override fun selecionou(repositorio: Repositorio) {
                    startActivity(
                        Intent(this@MainActivity, ForkActivity::class.java)
                            .putExtra("repositorio", repositorio)
                    )
                }

                override fun buscarMais(numeroDaPagina: Int) {
                    novaLista = false
                    numPage = numeroDaPagina
                    presenter.search(numeroDaPagina)
                }
            })
        val adapter = recyclerViewRepositorios.adapter as AdapterListaRepository
        adapter.setNumeroDaPaginaAtual(numPage)
    }


    override fun showErroResult(mensagem: String?) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

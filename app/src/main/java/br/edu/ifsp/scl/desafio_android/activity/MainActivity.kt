package br.edu.ifsp.scl.desafio_android.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.fragment.RepositoriesListFragment
import br.edu.ifsp.scl.desafio_android.util.ConfigSingleton.Modos.MY_REPOSITORY_MODE
import br.edu.ifsp.scl.desafio_android.util.ConfigSingleton.Modos.REPOSITORY_MODE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.repo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val abreFechaToogle = ActionBarDrawerToggle(this, menuLateralDrawerLayout, toolbar, R.string.open_menu, R.string.close_menu)
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)
        abreFechaToogle.syncState()
        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
        changeFragment(REPOSITORY_MODE)
    }

    private fun changeFragment(modo: String) {
        var modoSelecionado: Fragment? = null
        when(modo) {
            REPOSITORY_MODE -> {
                modoSelecionado = RepositoriesListFragment()
                supportActionBar?.title = "${resources.getString(R.string.repo)}"
            }
            MY_REPOSITORY_MODE -> {
                //modoSelecionado = ProdutoListFragment()
                //supportActionBar?.title = "${resources.getString(R.string.my_repo)}"
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, modoSelecionado!!, modo).commit()
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno = false
        when (item.itemId) {
            R.id.repo -> {
                changeFragment(MY_REPOSITORY_MODE)
                retorno = true
            }
            R.id.close -> {
                retorno = true
                finish()
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return retorno
    }
}

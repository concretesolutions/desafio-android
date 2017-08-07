package br.com.concrete.desafio.feature

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.concrete.desafio.extension.addStatusBarPadding
import kotlinx.android.synthetic.main.activity_repo_list.*

abstract class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        toolbar?.let {
            it.addStatusBarPadding()
            setSupportActionBar(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}